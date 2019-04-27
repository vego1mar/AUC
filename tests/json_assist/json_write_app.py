import helpers as hp


def _get_files_path(file_name):
    return r'../../runtime/' + file_name + '.json'


def _app_bethesda_net_launcher_to_json():
    import tests.executing.app_bethesda_net_launcher_test as bnl
    entry_1 = bnl.get_entry_1()
    entry_2 = bnl.get_entry_2()
    entry_1.html_data = bnl.BethesdaNetLauncherTestData.APP_WEBSITE
    entry_2.html_data = bnl.BethesdaNetLauncherTestData.WEB_SPACE_URL_2
    json_str_1 = entry_1.to_json()
    json_str_2 = entry_2.to_json()
    hp.save_file(_get_files_path("bethesda_net_launcher_1"), json_str_1)
    hp.save_file(_get_files_path("bethesda_net_launcher_2"), json_str_2)


def _app_blizzard_battle_net_to_json():
    import tests.executing.app_blizzard_battle_net_test as bbn
    entry_1 = bbn.get_entry_1()
    entry_2 = bbn.get_entry_2()
    entry_3 = bbn.get_entry_3()
    entry_4 = bbn.get_entry_4()
    entry_1.html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_1
    entry_2.html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_2
    entry_3.html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_3
    json_str_1 = entry_1.to_json()
    json_str_2 = entry_2.to_json()
    json_str_3 = entry_3.to_json()
    json_str_4 = entry_4.to_json()
    hp.save_file(_get_files_path("blizzard_battle_net_1"), json_str_1)
    hp.save_file(_get_files_path("blizzard_battle_net_2"), json_str_2)
    hp.save_file(_get_files_path("blizzard_battle_net_3"), json_str_3)
    hp.save_file(_get_files_path("blizzard_battle_net_4"), json_str_4)


def _save_apps_collector_prerequisites():
    _app_bethesda_net_launcher_to_json()
    _app_blizzard_battle_net_to_json()


_save_apps_collector_prerequisites()
