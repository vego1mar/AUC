import json
import executing as ex
import helpers as hp
import tests.console.json_files_aquisition as jfa


class HierarchyFile:
    FILE_PREFIX = r'../json/'
    OUTPUT_FILE_NAME = '__collectibles__.json'

    def __init__(self):
        self.hierarchy_dict = {}
        self._execution_order_list = []
        self._current_element = -1
        self._collectibles_list = []

    def load_hierarchy_file(self):
        print('Loading ' + jfa.HIERARCHY + ' information...')
        file_content = hp.read_file(HierarchyFile.FILE_PREFIX + jfa.HIERARCHY_FILE_NAME)
        self.hierarchy_dict = json.loads(file_content)

    def traverse_to_collect(self):
        self._read_json_files()
        self._fetch_html_data()
        self._collect_app_data()

    def _read_json_files(self):
        print('Reading JSON files...')

        for app_name in self.hierarchy_dict:
            self._read_execution_order(app_name)

    def _read_execution_order(self, app_name):
        self._current_element += 1
        self._execution_order_list.append(ex.ExecutionOrder())
        json_files = self.hierarchy_dict[app_name]

        for j in range(0, len(json_files)):
            file_with_json = hp.read_file(HierarchyFile.FILE_PREFIX + json_files[j])
            entry = ex.ExecutionOrderEntry.from_json(file_with_json)
            self._execution_order_list[self._current_element].add_entry(entry, True)

    def _fetch_html_data(self):
        print('Fetching HTML data...')

        for execution_order in self._execution_order_list:
            execution_order.fetch_html_data()

    def _collect_app_data(self):
        print('Collecting apps data...')
        i = 0

        for app_name in self.hierarchy_dict:
            collector = ex.InfoCollector(app_name, self._execution_order_list[i])
            collector.collect()
            self._collectibles_list.append(collector.get_collectibles())
            i += 1

    def save_collectibles(self):
        print('Saving collectibles to file...')
        app_names = []
        j = 0

        for app_name in self.hierarchy_dict:
            app_names.append(app_name)

        for collectible in self._collectibles_list:
            collectible['APP_NAME'] = app_names[j]
            j += 1

        encoder = json.JSONEncoder(indent=hp.get_json_indent())
        json_collect = encoder.encode(self._collectibles_list)
        hp.save_file(HierarchyFile.OUTPUT_FILE_NAME, json_collect)


def _main():
    hierarchy = HierarchyFile()
    HierarchyFile.OUTPUT_FILE_NAME = '../runtime/' + HierarchyFile.OUTPUT_FILE_NAME
    print('OUTPUT_FILE_NAME=' + HierarchyFile.OUTPUT_FILE_NAME)
    hierarchy.load_hierarchy_file()
    hierarchy.traverse_to_collect()
    hierarchy.save_collectibles()
    print('Done.')


if __name__ == '__main__':
    _main()
