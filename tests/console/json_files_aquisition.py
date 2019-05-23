import json
import helpers as hp

HIERARCHY = '__hierarchy__'
HIERARCHY_FILE_NAME = '__hierarchy__.json'
FILE_NAMES = dict()


def _get_extension():
    return '.json'


def _get_files_path(file_name):
    return r'../../runtime/' + file_name + _get_extension()


def _get_file_names(files_tuple):
    names = []

    for file_name in files_tuple:
        names.append(file_name + _get_extension())

    return names


def _app_bethesda_net_launcher_to_json():
    import tests.executing.app_bethesda_net_launcher_test as bnl
    files = ("bethesda_net_launcher_1", "bethesda_net_launcher_2")
    entries = [bnl.get_entry_1(), bnl.get_entry_2()]
    entries[0].html_data = bnl.BethesdaNetLauncherTestData.WEB_SPACE_URL_1
    entries[1].html_data = bnl.BethesdaNetLauncherTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    FILE_NAMES[bnl.BethesdaNetLauncherTestData.APP_NAME] = _get_file_names(files)


def _app_blizzard_battle_net_to_json():
    import tests.executing.app_blizzard_battle_net_test as bbn
    files = ("blizzard_battle_net_1", "blizzard_battle_net_2", "blizzard_battle_net_3", "blizzard_battle_net_4",
             "blizzard_battle_net_5")
    entries = [bbn.get_entry_1(), bbn.get_entry_2(), bbn.get_entry_3(), bbn.get_entry_4(), bbn.get_entry_5()]
    entries[0].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_1
    entries[1].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_2
    entries[2].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_3
    entries[3].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_4
    entries[4].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_5
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    hp.save_file(_get_files_path(files[3]), entries[3].to_json())
    hp.save_file(_get_files_path(files[4]), entries[4].to_json())
    FILE_NAMES[bbn.BlizzardBattleNetTestData.APP_NAME] = _get_file_names(files)


def _app_driver_booster_to_json():
    import tests.executing.app_driver_booster_test as db
    files = ("driver_booster_1", "driver_booster_2")
    entries = [db.get_entry_1(), db.get_entry_2()]
    entries[0].html_data = db.DriverBoosterTestData.WEB_SPACE_URL_1
    entries[1].html_data = db.DriverBoosterTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    FILE_NAMES[db.DriverBoosterTestData.APP_NAME] = _get_file_names(files)


def _app_dvdfab_virtual_drive_to_json():
    import tests.executing.app_dvdfab_virtual_drive_test as dfvd
    files = ("dvdfab_virtual_drive_1", "dvdfab_virtual_drive_2")
    entries = [dfvd.get_entry_1(), dfvd.get_entry_2()]
    entries[0].html_data = dfvd.DvdFabVirtualDriveTestData.WEB_SPACE_URL_1
    entries[1].html_data = dfvd.DvdFabVirtualDriveTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    FILE_NAMES[dfvd.DvdFabVirtualDriveTestData.APP_NAME] = _get_file_names(files)


def _app_epic_games_launcher_to_json():
    import tests.executing.app_epic_games_launcher_test as egl
    files = ("epic_games_launcher_1", "epic_games_launcher_2", "epic_games_launcher_3")
    entries = [egl.get_entry_1(), egl.get_entry_2(), egl.get_entry_3()]
    entries[0].html_data = egl.EpicGamesLauncherTestData.WEB_SPACE_URL_1
    entries[1].html_data = egl.EpicGamesLauncherTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    FILE_NAMES[egl.EpicGamesLauncherTestData.APP_NAME] = _get_file_names(files)


def _app_gog_galaxy_to_json():
    import tests.executing.app_gog_galaxy_test as gog
    files = ("gog_galaxy_1", "gog_galaxy_2", "gog_galaxy_3")
    entries = [gog.get_entry_1(), gog.get_entry_2(), gog.get_entry_1()]
    entries[0].html_data = gog.GOGGalaxyTestData.WEB_SPACE_URL_1
    entries[1].html_data = gog.GOGGalaxyTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    FILE_NAMES[gog.GOGGalaxyTestData.APP_NAME] = _get_file_names(files)


def _app_google_chrome_to_json():
    import tests.executing.app_google_chrome_test as gc
    files = ("google_chrome_1", "google_chrome_2", "google_chrome_3", "google_chrome_4", "google_chrome_5")
    entries = [gc.get_entry_1(), gc.get_entry_2(), gc.get_entry_3(), gc.get_entry_4(), gc.get_entry_5()]
    entries[1].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_2
    entries[2].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_3
    entries[3].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_4
    entries[4].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_5
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    hp.save_file(_get_files_path(files[3]), entries[3].to_json())
    hp.save_file(_get_files_path(files[4]), entries[4].to_json())
    FILE_NAMES[gc.GoogleChromeTestData.APP_NAME] = _get_file_names(files)


def _app_hashtab_to_json():
    import tests.executing.app_hashtab_test as ht
    files = ("hashtab_1", "hashtab_2")
    entries = [ht.get_entry_1(), ht.get_entry_2()]
    entries[0].html_data = ht.HashTabTestData.WEB_SPACE_URL_1
    entries[1].html_data = ht.HashTabTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    FILE_NAMES[ht.HashTabTestData.APP_NAME] = _get_file_names(files)


def _app_irfanview_to_json():
    import tests.executing.app_irfanview_test as iv
    files = ("irfanview_1", "irfanview_2", "irfanview_3", "irfanview_4", "irfanview_5")
    entries = [iv.get_entry_1(), iv.get_entry_2(), iv.get_entry_3(), iv.get_entry_4(), iv.get_entry_5()]
    entries[1].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_2
    entries[2].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_3
    entries[3].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_4
    entries[4].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_5
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    hp.save_file(_get_files_path(files[3]), entries[3].to_json())
    hp.save_file(_get_files_path(files[4]), entries[4].to_json())
    FILE_NAMES[iv.IrfanViewTestData.APP_NAME] = _get_file_names(files)


def _app_jetclean_to_json():
    import tests.executing.app_jetclean_test as jc
    files = ("jetclean_1", "jetclean_2", "jetclean_3", "jetclean_4")
    entries = [jc.get_entry_1(), jc.get_entry_2(), jc.get_entry_3(), jc.get_entry_4()]
    entries[1].html_data = jc.JetCleanTestData.WEB_SPACE_URL_2
    entries[2].html_data = jc.JetCleanTestData.WEB_SPACE_URL_3
    entries[3].html_data = jc.JetCleanTestData.WEB_SPACE_URL_4
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    hp.save_file(_get_files_path(files[3]), entries[3].to_json())
    FILE_NAMES[jc.JetCleanTestData.APP_NAME] = _get_file_names(files)


def _app_keepass_to_json():
    import tests.executing.app_keepass_test as kp
    files = ("keepass_1", "keepass_2", "keepass_3")
    entries = [kp.get_entry_1(), kp.get_entry_2(), kp.get_entry_3()]
    entries[1].html_data = kp.KeePassTestData.WEB_SPACE_URL_2
    entries[2].html_data = kp.KeePassTestData.WEB_SPACE_URL_3
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    FILE_NAMES[kp.KeePassTestData.APP_NAME] = _get_file_names(files)


def _app_malware_fighter_to_json():
    import tests.executing.app_malware_fighter_test as mf
    files = ("malware_fighter_1", "malware_fighter_2")
    entries = [mf.get_entry_1(), mf.get_entry_2()]
    entries[1].html_data = mf.MalwareFighterTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    FILE_NAMES[mf.MalwareFighterTestData.APP_NAME] = _get_file_names(files)


def _app_origin_to_json():
    import tests.executing.app_origin_test as o
    files = ("origin_1", "origin_2", "origin_3")
    entries = [o.get_entry_1(), o.get_entry_2(), o.get_entry_3()]
    entries[0].html_data = o.OriginTestData.WEB_SPACE_URL_1
    entries[1].html_data = o.OriginTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    FILE_NAMES[o.OriginTestData.APP_NAME] = _get_file_names(files)


def _app_potplayer_to_json():
    import tests.executing.app_potplayer_test as pp
    files = ("potplayer_1", "potplayer_2")
    entries = [pp.get_entry_1(), pp.get_entry_2()]
    entries[0].html_data = pp.PotPlayerTestData.WEB_SPACE_URL_1
    entries[1].html_data = pp.PotPlayerTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    FILE_NAMES[pp.PotPlayerTestData.APP_NAME] = _get_file_names(files)


def _app_smart_defrag_to_json():
    import tests.executing.app_smart_defrag_test as sd
    files = ("smart_defrag_1", "smart_defrag_2", "smart_defrag_3")
    entries = [sd.get_entry_1(), sd.get_entry_2(), sd.get_entry_3()]
    entries[1].html_data = sd.SmartDefragTestData.WEB_SPACE_URL_2
    entries[2].html_data = sd.SmartDefragTestData.WEB_SPACE_URL_3
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    FILE_NAMES[sd.SmartDefragTestData.APP_NAME] = _get_file_names(files)


def _app_speccy_to_json():
    import tests.executing.app_speccy_test as sd
    files = ("speccy_1", "speccy_2", "speccy_3")
    entries = [sd.get_entry_1(), sd.get_entry_2(), sd.get_entry_3()]
    entries[1].html_data = sd.SpeccyTestData.WEB_SPACE_URL_2
    entries[2].html_data = sd.SpeccyTestData.WEB_SPACE_URL_3
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    FILE_NAMES[sd.SpeccyTestData.APP_NAME] = _get_file_names(files)


def _app_steam_to_json():
    import tests.executing.app_steam_test as s
    files = ("steam_1", "steam_2", "steam_3", "steam_4", "steam_5", "steam_6")
    entries = [s.get_entry_1(), s.get_entry_2(), s.get_entry_3(), s.get_entry_4(), s.get_entry_5(), s.get_entry_6()]
    entries[0].html_data = s.SteamTestData.WEB_SPACE_URL_1
    entries[1].html_data = s.SteamTestData.WEB_SPACE_URL_2
    entries[2].html_data = s.SteamTestData.WEB_SPACE_URL_3
    entries[3].html_data = s.SteamTestData.WEB_SPACE_URL_4
    entries[4].html_data = s.SteamTestData.WEB_SPACE_URL_5
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    hp.save_file(_get_files_path(files[3]), entries[3].to_json())
    hp.save_file(_get_files_path(files[4]), entries[4].to_json())
    hp.save_file(_get_files_path(files[5]), entries[5].to_json())
    FILE_NAMES[s.SteamTestData.APP_NAME] = _get_file_names(files)


def _app_sumatrapdf_to_json():
    import tests.executing.app_sumatrapdf_test as sp
    files = ("sumatrapdf_1", "sumatrapdf_2", "sumatrapdf_3", "sumatrapdf_4", "sumatrapdf_5", "sumatrapdf_6",
             "sumatrapdf_7", "sumatrapdf_8", "sumatrapdf_9")
    entries = [sp.get_entry_1(), sp.get_entry_2(), sp.get_entry_3(), sp.get_entry_4(), sp.get_entry_5(),
               sp.get_entry_6(), sp.get_entry_7(), sp.get_entry_8(), sp.get_entry_9()]
    entries[1].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_2
    entries[2].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_3
    entries[3].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_4
    entries[4].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_5
    entries[5].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_6
    entries[6].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_7
    entries[7].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_8
    entries[8].html_data = sp.SumatraPdfTestData.WEB_SPACE_URL_9
    hp.save_file(_get_files_path(files[0]), entries[0].to_json())
    hp.save_file(_get_files_path(files[1]), entries[1].to_json())
    hp.save_file(_get_files_path(files[2]), entries[2].to_json())
    hp.save_file(_get_files_path(files[3]), entries[3].to_json())
    hp.save_file(_get_files_path(files[4]), entries[4].to_json())
    hp.save_file(_get_files_path(files[5]), entries[5].to_json())
    hp.save_file(_get_files_path(files[6]), entries[6].to_json())
    hp.save_file(_get_files_path(files[7]), entries[7].to_json())
    hp.save_file(_get_files_path(files[8]), entries[8].to_json())
    FILE_NAMES[sp.SumatraPdfTestData.APP_NAME] = _get_file_names(files)


def _save_apps_collector_prerequisites():
    _app_bethesda_net_launcher_to_json()
    _app_blizzard_battle_net_to_json()
    _app_driver_booster_to_json()
    _app_dvdfab_virtual_drive_to_json()
    _app_epic_games_launcher_to_json()
    _app_gog_galaxy_to_json()
    _app_google_chrome_to_json()
    _app_hashtab_to_json()
    _app_irfanview_to_json()
    _app_jetclean_to_json()
    _app_keepass_to_json()
    _app_malware_fighter_to_json()
    _app_origin_to_json()
    _app_potplayer_to_json()
    _app_smart_defrag_to_json()
    _app_speccy_to_json()
    _app_steam_to_json()
    _app_sumatrapdf_to_json()


def _create_hierarchy_file_as_json():
    file_name = _get_files_path(HIERARCHY)
    encoder = json.JSONEncoder(indent=hp.get_json_indent())
    hp.save_file(file_name, encoder.encode(FILE_NAMES))


if __name__ == '__main__':
    print('Saving apps JSON files...')
    _save_apps_collector_prerequisites()
    _create_hierarchy_file_as_json()
    print('Done.')
