import argparse
import json
import executing as ex
import helpers as hp


class HierarchyFile:
    FILE_PREFIX = r'../json/'
    HIERARCHY_FILE_NAME = '__hierarchy__.json'
    OUTPUT_FILE_NAME = '__collectibles__.json'

    def __init__(self):
        self.hierarchy_dict = {}
        self._execution_order_list = []
        self._current_element = -1
        self._collectibles_list = []

    def load_hierarchy_file(self):
        file_content = hp.read_file(HierarchyFile.FILE_PREFIX + HierarchyFile.HIERARCHY_FILE_NAME)
        self.hierarchy_dict = json.loads(file_content)

    def traverse_to_collect(self):
        self._read_json_files()
        self._fetch_html_data()
        self._collect_app_data()

    def _read_json_files(self):
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
        for execution_order in self._execution_order_list:
            execution_order.fetch_html_data()

    def _collect_app_data(self):
        i = 0

        for app_name in self.hierarchy_dict:
            collector = ex.InfoCollector(app_name, self._execution_order_list[i])
            collector.collect()
            self._collectibles_list.append(collector.get_collectibles())
            i += 1

    def save_collectibles(self):
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


def _get_command_line_ars():
    parser = argparse.ArgumentParser()
    parser.add_argument('--input-file-prefix', default='', type=str, metavar=HierarchyFile.HIERARCHY_FILE_NAME)
    parser.add_argument('--output-file-prefix', default='', type=str, metavar=HierarchyFile.OUTPUT_FILE_NAME)
    parser.add_argument('--verbose', action='store_true')
    parser.add_argument('--version', action='version', version='2.0')
    return parser.parse_args()


def _print_progress():
    print('.', end='')


def _provide_test_prefix_at_empty_args(args):
    if str(args.output_file_prefix) == str() and str() == str(args.input_file_prefix):
        args.output_file_prefix = '../runtime/'


def _print_file_paths(args):
    if args.verbose:
        print()
        print('INPUT_FILE_PATH=' + args.input_file_prefix + HierarchyFile.HIERARCHY_FILE_NAME)
        print('OUTPUT_FILE_PATH=' + args.output_file_prefix + HierarchyFile.OUTPUT_FILE_NAME)


def _main(args):
    _print_progress()
    hierarchy = HierarchyFile()
    _provide_test_prefix_at_empty_args(args)
    _print_progress()
    HierarchyFile.FILE_PREFIX = args.input_file_prefix
    HierarchyFile.OUTPUT_FILE_NAME = args.output_file_prefix + HierarchyFile.OUTPUT_FILE_NAME
    _print_file_paths(args)
    _print_progress()
    hierarchy.load_hierarchy_file()
    _print_progress()
    hierarchy.traverse_to_collect()
    _print_progress()
    hierarchy.save_collectibles()


if __name__ == '__main__':
    _main(_get_command_line_ars())
    _print_progress()
