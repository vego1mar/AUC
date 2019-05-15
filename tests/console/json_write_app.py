import helpers as hp


def _get_files_path(file_name):
    return r'../../runtime/' + file_name + '.json'


def _app_bethesda_net_launcher_to_json():
    import tests.executing.app_bethesda_net_launcher_test as bnl
    entries = [bnl.get_entry_1(), bnl.get_entry_2()]
    entries[0].html_data = bnl.BethesdaNetLauncherTestData.APP_WEBSITE
    entries[1].html_data = bnl.BethesdaNetLauncherTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("bethesda_net_launcher_1"), entries[0].to_json())
    hp.save_file(_get_files_path("bethesda_net_launcher_2"), entries[1].to_json())


def _app_blizzard_battle_net_to_json():
    import tests.executing.app_blizzard_battle_net_test as bbn
    entries = [bbn.get_entry_1(), bbn.get_entry_2(), bbn.get_entry_3(), bbn.get_entry_4()]
    entries[0].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_1
    entries[1].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_2
    entries[2].html_data = bbn.BlizzardBattleNetTestData.WEB_SPACE_URL_3
    hp.save_file(_get_files_path("blizzard_battle_net_1"), entries[0].to_json())
    hp.save_file(_get_files_path("blizzard_battle_net_2"), entries[1].to_json())
    hp.save_file(_get_files_path("blizzard_battle_net_3"), entries[2].to_json())
    hp.save_file(_get_files_path("blizzard_battle_net_4"), entries[3].to_json())


def _app_driver_booster_to_json():
    import tests.executing.app_driver_booster_test as db
    entries = [db.get_entry_1(), db.get_entry_2()]
    entries[0].html_data = db.DriverBoosterTestData.WEB_SPACE_URL_1
    entries[1].html_data = db.DriverBoosterTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("driver_booster_1"), entries[0].to_json())
    hp.save_file(_get_files_path("driver_booster_2"), entries[1].to_json())


def _app_dvdfab_virtual_drive_to_json():
    import tests.executing.app_dvdfab_virtual_drive_test as dfvd
    entries = [dfvd.get_entry_1(), dfvd.get_entry_2()]
    entries[0].html_data = dfvd.DvdFabVirtualDriveTestData.WEB_SPACE_URL_1
    entries[1].html_data = dfvd.DvdFabVirtualDriveTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("dvdfab_virtual_drive_1"), entries[0].to_json())
    hp.save_file(_get_files_path("dvdfab_virtual_drive_2"), entries[1].to_json())


def _app_epic_games_launcher_to_json():
    import tests.executing.app_epic_games_launcher_test as egl
    entries = [egl.get_entry_1(), egl.get_entry_2(), egl.get_entry_3()]
    entries[0].html_data = egl.EpicGamesLauncherTestData.WEB_SPACE_URL_1
    entries[1].html_data = egl.EpicGamesLauncherTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("epic_games_launcher_1"), entries[0].to_json())
    hp.save_file(_get_files_path("epic_games_launcher_2"), entries[1].to_json())
    hp.save_file(_get_files_path("epic_games_launcher_3"), entries[2].to_json())


def _app_gog_galaxy_to_json():
    import tests.executing.app_gog_galaxy_test as gog
    entries = [gog.get_entry_1(), gog.get_entry_2(), gog.get_entry_3()]
    entries[0].html_data = gog.GOGGalaxyTestData.WEB_SPACE_URL_1
    entries[1].html_data = gog.GOGGalaxyTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("gog_galaxy_1"), entries[0].to_json())
    hp.save_file(_get_files_path("gog_galaxy_2"), entries[1].to_json())
    hp.save_file(_get_files_path("gog_galaxy_3"), entries[2].to_json())


def _app_google_chrome_to_json():
    import tests.executing.app_google_chrome_test as gc
    entries = [gc.get_entry_1(), gc.get_entry_2(), gc.get_entry_3(), gc.get_entry_4(), gc.get_entry_5()]
    entries[1].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_2
    entries[2].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_3
    entries[3].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_4
    entries[4].html_data = gc.GoogleChromeTestData.WEB_SPACE_URL_5
    hp.save_file(_get_files_path("google_chrome_1"), entries[0].to_json())
    hp.save_file(_get_files_path("google_chrome_2"), entries[1].to_json())
    hp.save_file(_get_files_path("google_chrome_3"), entries[2].to_json())
    hp.save_file(_get_files_path("google_chrome_4"), entries[3].to_json())
    hp.save_file(_get_files_path("google_chrome_5"), entries[4].to_json())


def _app_hashtab_to_json():
    import tests.executing.app_hashtab_test as ht
    entries = [ht.get_entry_1(), ht.get_entry_2()]
    entries[0].html_data = ht.HashTabTestData.WEB_SPACE_URL_1
    entries[1].html_data = ht.HashTabTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("hashtab_1"), entries[0].to_json())
    hp.save_file(_get_files_path("hashtab_2"), entries[1].to_json())


def _app_irfanview_to_json():
    import tests.executing.app_irfanview_test as iv
    entries = [iv.get_entry_1(), iv.get_entry_2(), iv.get_entry_3(), iv.get_entry_4(), iv.get_entry_5()]
    entries[1].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_2
    entries[2].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_3
    entries[3].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_4
    entries[4].html_data = iv.IrfanViewTestData.WEB_SPACE_URL_5
    hp.save_file(_get_files_path("irfanview_1"), entries[0].to_json())
    hp.save_file(_get_files_path("irfanview_2"), entries[1].to_json())
    hp.save_file(_get_files_path("irfanview_3"), entries[2].to_json())
    hp.save_file(_get_files_path("irfanview_4"), entries[3].to_json())
    hp.save_file(_get_files_path("irfanview_5"), entries[4].to_json())


def _app_jetclean_to_json():
    import tests.executing.app_jetclean_test as jc
    entries = [jc.get_entry_1(), jc.get_entry_2(), jc.get_entry_3(), jc.get_entry_4()]
    entries[1].html_data = jc.JetCleanTestData.WEB_SPACE_URL_2
    entries[2].html_data = jc.JetCleanTestData.WEB_SPACE_URL_3
    entries[3].html_data = jc.JetCleanTestData.WEB_SPACE_URL_4
    hp.save_file(_get_files_path("jetclean_1"), entries[0].to_json())
    hp.save_file(_get_files_path("jetclean_2"), entries[1].to_json())
    hp.save_file(_get_files_path("jetclean_3"), entries[2].to_json())
    hp.save_file(_get_files_path("jetclean_4"), entries[3].to_json())


def _app_keepass_to_json():
    import tests.executing.app_keepass_test as kp
    entries = [kp.get_entry_1(), kp.get_entry_2(), kp.get_entry_3()]
    entries[1].html_data = kp.KeePassTestData.WEB_SPACE_URL_2
    entries[2].html_data = kp.KeePassTestData.WEB_SPACE_URL_3
    hp.save_file(_get_files_path("keepass_1"), entries[0].to_json())
    hp.save_file(_get_files_path("keepass_2"), entries[1].to_json())
    hp.save_file(_get_files_path("keepass_3"), entries[2].to_json())


def _app_malware_fighter_to_json():
    import tests.executing.app_malware_fighter_test as mf
    entries = [mf.get_entry_1(), mf.get_entry_2()]
    entries[1].html_data = mf.MalwareFighterTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("malware_fighter_1"), entries[0].to_json())
    hp.save_file(_get_files_path("malware_fighter_2"), entries[1].to_json())


def _app_origin_to_json():
    import tests.executing.app_origin_test as o
    entries = [o.get_entry_1(), o.get_entry_2(), o.get_entry_3()]
    entries[0].html_data = o.OriginTestData.WEB_SPACE_URL_1
    entries[1].html_data = o.OriginTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("origin_1"), entries[0].to_json())
    hp.save_file(_get_files_path("origin_2"), entries[1].to_json())
    hp.save_file(_get_files_path("origin_3"), entries[2].to_json())


def _app_potplayer_to_json():
    import tests.executing.app_potplayer_test as pp
    entries = [pp.get_entry_1(), pp.get_entry_2()]
    entries[0].html_data = pp.PotPlayerTestData.WEB_SPACE_URL_1
    entries[1].html_data = pp.PotPlayerTestData.WEB_SPACE_URL_2
    hp.save_file(_get_files_path("potplayer_1"), entries[0].to_json())
    hp.save_file(_get_files_path("potplayer_2"), entries[1].to_json())


def _app_smart_defrag_to_json():
    import tests.executing.app_smart_defrag_test as sd
    entries = [sd.get_entry_1(), sd.get_entry_2(), sd.get_entry_3()]
    entries[1].html_data = sd.SmartDefragTestData.WEB_SPACE_URL_2
    entries[2].html_data = sd.SmartDefragTestData.WEB_SPACE_URL_3
    hp.save_file(_get_files_path("smart_defrag_1"), entries[0].to_json())
    hp.save_file(_get_files_path("smart_defrag_2"), entries[1].to_json())
    hp.save_file(_get_files_path("smart_defrag_3"), entries[2].to_json())


def _app_speccy_to_json():
    import tests.executing.app_speccy_test as sd
    entries = [sd.get_entry_1(), sd.get_entry_2(), sd.get_entry_3()]
    entries[1].html_data = sd.SpeccyTestData.WEB_SPACE_URL_2
    entries[2].html_data = sd.SpeccyTestData.WEB_SPACE_URL_3
    hp.save_file(_get_files_path("speccy_1"), entries[0].to_json())
    hp.save_file(_get_files_path("speccy_2"), entries[1].to_json())
    hp.save_file(_get_files_path("speccy_3"), entries[2].to_json())


def _app_steam_to_json():
    import tests.executing.app_steam_test as s
    entries = [s.get_entry_1(), s.get_entry_2(), s.get_entry_3(), s.get_entry_4(), s.get_entry_5(), s.get_entry_6()]
    entries[0].html_data = s.SteamTestData.WEB_SPACE_URL_1
    entries[1].html_data = s.SteamTestData.WEB_SPACE_URL_2
    entries[2].html_data = s.SteamTestData.WEB_SPACE_URL_3
    entries[3].html_data = s.SteamTestData.WEB_SPACE_URL_4
    entries[4].html_data = s.SteamTestData.WEB_SPACE_URL_5
    hp.save_file(_get_files_path("steam_1"), entries[0].to_json())
    hp.save_file(_get_files_path("steam_2"), entries[1].to_json())
    hp.save_file(_get_files_path("steam_3"), entries[2].to_json())
    hp.save_file(_get_files_path("steam_4"), entries[3].to_json())
    hp.save_file(_get_files_path("steam_5"), entries[4].to_json())
    hp.save_file(_get_files_path("steam_6"), entries[5].to_json())


def _app_sumatrapdf_to_json():
    import tests.executing.app_sumatrapdf_test as sp
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
    hp.save_file(_get_files_path("sumatrapdf_1"), entries[0].to_json())
    hp.save_file(_get_files_path("sumatrapdf_2"), entries[1].to_json())
    hp.save_file(_get_files_path("sumatrapdf_3"), entries[2].to_json())
    hp.save_file(_get_files_path("sumatrapdf_4"), entries[3].to_json())
    hp.save_file(_get_files_path("sumatrapdf_5"), entries[4].to_json())
    hp.save_file(_get_files_path("sumatrapdf_6"), entries[5].to_json())
    hp.save_file(_get_files_path("sumatrapdf_7"), entries[6].to_json())
    hp.save_file(_get_files_path("sumatrapdf_8"), entries[7].to_json())
    hp.save_file(_get_files_path("sumatrapdf_9"), entries[8].to_json())


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


_save_apps_collector_prerequisites()
