import logging
from .requesting import TargetSetName
from .requesting import SetSpaces
from .helpers import fetch_html


class ChainRequestExecution:
    def __init__(self, web_page):
        self._set_spaces = SetSpaces()
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
            logging.info(trigger.to_string())
            self._alter_target_space(target, trigger)
            self._acquire_collectible(target)

    def _alter_target_space(self, target, trigger):
        if target.set_name in [TargetSetName.WEB_SPACE, TargetSetName.WORK_SPACE]:
            self._set_spaces.work_space = trigger.get_result()
        elif target.set_name == TargetSetName.LIST_SPACE:
            self._set_spaces.list_space = trigger.get_result()
        else:
            raise ValueError("Not supported set space.")

    def _acquire_collectible(self, target):
        if target.is_gathering_request:
            self._collectibles[target.collectible_name] = self._set_spaces.work_space


class ExecutionOrderEntry:
    def __init__(self, chain_request, html_data):
        self.chain_request = chain_request
        self.html_data = str(html_data)


class ExecutionOrder:
    def __init__(self):
        self.list = list()

    def add_entry(self, execution_order_entry, is_html_fetched=False):
        if is_html_fetched:
            self.list.append(execution_order_entry)
            return None

        url = execution_order_entry.html_data
        execution_order_entry.html_data = fetch_html(url)
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
