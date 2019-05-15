import json
import helpers as hp
import tests.console.json_files_aquisition as jfa


# TODO: load_json_file() using __hierarchy__.json file
# TODO: propagate_objects()
# TODO: collect()
# TODO: save_collectibles()

def _get_path_prefix():
    return r'../json/'


def _load_hierarchy_file():
    hierarchy_file = hp.fetch_file(_get_path_prefix() + jfa.HIERARCHY_FILE_NAME)
    hierarchy_file = hierarchy_file[2:len(hierarchy_file) - 2]
    hierarchy_file = hierarchy_file.replace("','", '', hierarchy_file.count("','"))
    # TODO: handle multi-line file loading
    hierarchy_dict = json.loads(hierarchy_file)
    print('Done.')


_load_hierarchy_file()
