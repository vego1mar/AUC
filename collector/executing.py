import json
import requesting as rq
import helpers as hp


class ChainRequestExecution:
    def __init__(self, web_page):
        self._set_spaces = rq.SetSpaces()
        self._set_spaces.web_space = web_page
        self._chain_request = []
        self._collectibles = {}

    def set_chain_request(self, chain_request):
        if isinstance(chain_request, tuple) or isinstance(chain_request, type([])):
            self._chain_request = chain_request

    def get_collectibles(self):
        return self._collectibles

    def execute(self):
        for request in self._chain_request:
            target = request.target
            trigger = request.trigger
            trigger.invoke(target, self._set_spaces)
            self._alter_target_space(target, trigger)
            self._acquire_collectible(target)

    def _alter_target_space(self, target, trigger):
        if target.set_name in [rq.SpaceName.WEB, rq.SpaceName.WORK]:
            self._set_spaces.work_space = trigger.get_result()
        elif target.set_name == rq.SpaceName.LIST:
            self._set_spaces.work_space = trigger.get_result()
            self._set_spaces.list_space = trigger.get_result_list()
        else:
            raise ValueError("Not supported set space.")

    def _acquire_collectible(self, target):
        if target.is_gathering_request:
            self._collectibles[target.collectible_name] = self._set_spaces.work_space


class ExecutionOrderEntry(json.JSONEncoder):
    CHAIN_REQUEST = 'chain_request'
    HTML_DATA = 'html_data'

    def __init__(self, chain_request, html_data):
        super().__init__(indent=hp.get_json_indent())
        self.chain_request = chain_request
        self.html_data = str(html_data)

    def default(self, o):
        return self.to_dict()

    def to_json(self):
        return self.encode(self.to_dict())

    def to_dict(self):
        this = dict()
        chain_request = []

        for req in self.chain_request:
            chain_request.append(req.to_dict())

        this[ExecutionOrderEntry.CHAIN_REQUEST] = chain_request
        this[ExecutionOrderEntry.HTML_DATA] = self.html_data
        return this

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return ExecutionOrderEntry.from_dict(json_dict)

    @classmethod
    def from_dict(cls, dct):
        chain_request = dct[ExecutionOrderEntry.CHAIN_REQUEST]
        chain_arr = []

        for request in chain_request:
            current_req = rq.InvocationRequest.from_dict(request)
            chain_arr.append(current_req)

        html_data = dct[ExecutionOrderEntry.HTML_DATA]
        return cls(chain_arr, html_data)

    def compare(self, obj):
        if not isinstance(obj, ExecutionOrderEntry):
            return False

        if len(self.chain_request) != len(obj.chain_request):
            return False

        result_1 = True

        for i in range(0, len(self.chain_request)):
            result_1 = result_1 and self.chain_request[i].compare(obj.chain_request[i])

        result_2 = self.html_data == obj.html_data
        return result_1 and result_2


class ExecutionOrder:
    def __init__(self):
        self.list = list()

    def add_entry(self, execution_order_entry, is_html_fetched=False):
        if is_html_fetched:
            self.list.append(execution_order_entry)
            return None

        url = execution_order_entry.html_data
        execution_order_entry.html_data = hp.fetch_html(url)
        self.list.append(execution_order_entry)

    def __iter__(self):
        return iter(self.list)


class InfoCollector:
    def __init__(self, app_name, execution_order):
        self._app_name = str(app_name)
        self._execution_order = execution_order
        self._collectibles = dict()

    def collect(self):
        if not isinstance(self._execution_order, ExecutionOrder):
            return

        for i in range(0, len(self._execution_order.list)):
            entry = self._execution_order.list[i]
            executor = ChainRequestExecution(entry.html_data)
            executor.set_chain_request(entry.chain_request)
            executor.execute()
            self._update_collectibles(executor.get_collectibles())

    def _update_collectibles(self, current_collectible):
        for name, value in current_collectible.items():
            self._collectibles[name] = value

    def get_collectibles(self):
        return self._collectibles

    def get_app_name(self):
        return self._app_name
