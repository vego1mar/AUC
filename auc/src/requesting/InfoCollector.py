from urllib.request import urlopen
from auc.src.requesting.ChainRequestExecution import ChainRequestExecution


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
        execution_order_entry.html_data = self._fetch_html(url)
        self.list.append(execution_order_entry)

    def _fetch_html(self, url):
        with urlopen(url) as connection:
            return connection.read()

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
