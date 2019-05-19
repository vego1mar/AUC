import tests.executing.app_bethesda_net_launcher_test as app_1
import tests.executing.app_blizzard_battle_net_test as app_2
import tests.executing.app_driver_booster_test as app_3
import tests.executing.app_dvdfab_virtual_drive_test as app_4
import tests.executing.app_epic_games_launcher_test as app_5
import tests.executing.app_gog_galaxy_test as app_6
import tests.executing.app_google_chrome_test as app_7
import tests.executing.app_hashtab_test as app_8
import tests.executing.app_irfanview_test as app_9
import tests.executing.app_jetclean_test as app_10
import tests.executing.app_keepass_test as app_11
import tests.executing.app_malware_fighter_test as app_12
import tests.executing.app_origin_test as app_13
import tests.executing.app_potplayer_test as app_14
import tests.executing.app_smart_defrag_test as app_15
import tests.executing.app_speccy_test as app_16
import tests.executing.app_steam_test as app_17
import tests.executing.app_sumatrapdf_test as app_18
import helpers as hp


def update_html_for_app_tests():
    _update_html_for_bethesda_net_launcher_app()
    _update_html_for_blizzard_battle_net_app()
    _update_html_for_driver_booster_app()
    _update_html_for_dvdfab_virtual_drive_app()
    _update_html_for_epic_games_launcher_app()
    _update_html_for_gog_galaxy_app()
    _update_html_for_google_chrome_app()
    _update_html_for_hashtab_app()
    _update_html_for_irfanview_app()
    _update_html_for_jetclean_app()
    _update_html_for_keepass_app()
    _update_html_for_malware_fighter_app()
    _update_html_for_origin_app()
    _update_html_for_potplayer_app()
    _update_html_for_smart_defrag_app()
    _update_html_for_speccy_app()
    _update_html_for_steam_app()
    _update_html_for_sumatrapdf_app()


def _get_base64_str(file):
    base64_encoded = hp.encode_base64(file)
    content = str(base64_encoded)
    return content[2:len(content) - 1]


def _update_app_single_html(full_url, file_name):
    file = hp.fetch_html(full_url)
    hp.save_file(file_name, _get_base64_str(file))


def _traverse_dict_to_update(source_target):
    for url, path in source_target.items():
        _update_app_single_html(url, path)


def _update_html_for_bethesda_net_launcher_app():
    _traverse_dict_to_update({
        app_1.BethesdaNetLauncherTestData.WEB_SPACE_URL_2: app_1.BethesdaNetLauncherTestData.WEB_SPACE_HTML_PATH_2
    })


def _update_html_for_blizzard_battle_net_app():
    _traverse_dict_to_update({
        app_2.BlizzardBattleNetTestData.WEB_SPACE_URL_1: app_2.BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_1,
        app_2.BlizzardBattleNetTestData.WEB_SPACE_URL_2: app_2.BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2,
        app_2.BlizzardBattleNetTestData.WEB_SPACE_URL_3: app_2.BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3,
        app_2.BlizzardBattleNetTestData.WEB_SPACE_URL_4: app_2.BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_4,
        app_2.BlizzardBattleNetTestData.WEB_SPACE_URL_5: app_2.BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_5
    })


def _update_html_for_driver_booster_app():
    _traverse_dict_to_update({
        app_3.DriverBoosterTestData.WEB_SPACE_URL_2: app_3.DriverBoosterTestData.WEB_SPACE_HTML_PATH_2
    })


def _update_html_for_dvdfab_virtual_drive_app():
    _traverse_dict_to_update({
        app_4.DvdFabVirtualDriveTestData.WEB_SPACE_URL_1: app_4.DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_1,
        app_4.DvdFabVirtualDriveTestData.WEB_SPACE_URL_2: app_4.DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_2
    })


def _update_html_for_epic_games_launcher_app():
    _traverse_dict_to_update({
        app_5.EpicGamesLauncherTestData.WEB_SPACE_URL_1: app_5.EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_1,
        app_5.EpicGamesLauncherTestData.WEB_SPACE_URL_2: app_5.EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_2
    })


def _update_html_for_gog_galaxy_app():
    _traverse_dict_to_update({
        app_6.GOGGalaxyTestData.WEB_SPACE_URL_1: app_6.GOGGalaxyTestData.WEB_SPACE_HTML_PATH_1,
        app_6.GOGGalaxyTestData.WEB_SPACE_URL_2: app_6.GOGGalaxyTestData.WEB_SPACE_HTML_PATH_2
    })


def _update_html_for_google_chrome_app():
    _traverse_dict_to_update({
        app_7.GoogleChromeTestData.WEB_SPACE_URL_2: app_7.GoogleChromeTestData.WEB_SPACE_HTML_PATH_2,
        app_7.GoogleChromeTestData.WEB_SPACE_URL_3: app_7.GoogleChromeTestData.WEB_SPACE_HTML_PATH_3,
        app_7.GoogleChromeTestData.WEB_SPACE_URL_4: app_7.GoogleChromeTestData.WEB_SPACE_HTML_PATH_4,
        app_7.GoogleChromeTestData.WEB_SPACE_URL_5: app_7.GoogleChromeTestData.WEB_SPACE_HTML_PATH_5,
    })


def _update_html_for_hashtab_app():
    _traverse_dict_to_update({
        app_8.HashTabTestData.WEB_SPACE_URL_1: app_8.HashTabTestData.WEB_SPACE_HTML_PATH_1,
        app_8.HashTabTestData.WEB_SPACE_URL_2: app_8.HashTabTestData.WEB_SPACE_HTML_PATH_2
    })


def _update_html_for_irfanview_app():
    _traverse_dict_to_update({
        app_9.IrfanViewTestData.WEB_SPACE_URL_2: app_9.IrfanViewTestData.WEB_SPACE_HTML_PATH_2,
        app_9.IrfanViewTestData.WEB_SPACE_URL_3: app_9.IrfanViewTestData.WEB_SPACE_HTML_PATH_3,
        app_9.IrfanViewTestData.WEB_SPACE_URL_4: app_9.IrfanViewTestData.WEB_SPACE_HTML_PATH_4,
        app_9.IrfanViewTestData.WEB_SPACE_URL_5: app_9.IrfanViewTestData.WEB_SPACE_HTML_PATH_5
    })


def _update_html_for_jetclean_app():
    _traverse_dict_to_update({
        app_10.JetCleanTestData.WEB_SPACE_URL_2: app_10.JetCleanTestData.WEB_SPACE_HTML_PATH_2,
        app_10.JetCleanTestData.WEB_SPACE_URL_3: app_10.JetCleanTestData.WEB_SPACE_HTML_PATH_3,
        app_10.JetCleanTestData.WEB_SPACE_URL_4: app_10.JetCleanTestData.WEB_SPACE_HTML_PATH_4
    })


def _update_html_for_keepass_app():
    _traverse_dict_to_update({
        app_11.KeePassTestData.WEB_SPACE_URL_2: app_11.KeePassTestData.WEB_SPACE_HTML_PATH_2,
        app_11.KeePassTestData.WEB_SPACE_URL_3: app_11.KeePassTestData.WEB_SPACE_HTML_PATH_3,
    })


def _update_html_for_malware_fighter_app():
    _traverse_dict_to_update({
        app_12.MalwareFighterTestData.WEB_SPACE_URL_2: app_12.MalwareFighterTestData.WEB_SPACE_HTML_PATH_2,
    })


def _update_html_for_origin_app():
    _traverse_dict_to_update({
        app_13.OriginTestData.WEB_SPACE_URL_1: app_13.OriginTestData.WEB_SPACE_HTML_PATH_1,
        app_13.OriginTestData.WEB_SPACE_URL_2: app_13.OriginTestData.WEB_SPACE_HTML_PATH_2,
    })


def _update_html_for_potplayer_app():
    _traverse_dict_to_update({
        app_14.PotPlayerTestData.WEB_SPACE_URL_1: app_14.PotPlayerTestData.WEB_SPACE_HTML_PATH_1,
        app_14.PotPlayerTestData.WEB_SPACE_URL_2: app_14.PotPlayerTestData.WEB_SPACE_HTML_PATH_2,
    })


def _update_html_for_smart_defrag_app():
    _traverse_dict_to_update({
        app_15.SmartDefragTestData.WEB_SPACE_URL_2: app_15.SmartDefragTestData.WEB_SPACE_HTML_PATH_2,
        app_15.SmartDefragTestData.WEB_SPACE_URL_3: app_15.SmartDefragTestData.WEB_SPACE_HTML_PATH_3,
    })


def _update_html_for_speccy_app():
    _traverse_dict_to_update({
        app_16.SpeccyTestData.WEB_SPACE_URL_2: app_16.SpeccyTestData.WEB_SPACE_HTML_PATH_2,
        app_16.SpeccyTestData.WEB_SPACE_URL_3: app_16.SpeccyTestData.WEB_SPACE_HTML_PATH_3,
    })


def _update_html_for_steam_app():
    _traverse_dict_to_update({
        app_17.SteamTestData.WEB_SPACE_URL_1: app_17.SteamTestData.WEB_SPACE_HTML_PATH_1,
        app_17.SteamTestData.WEB_SPACE_URL_2: app_17.SteamTestData.WEB_SPACE_HTML_PATH_2,
        app_17.SteamTestData.WEB_SPACE_URL_3: app_17.SteamTestData.WEB_SPACE_HTML_PATH_3,
        app_17.SteamTestData.WEB_SPACE_URL_4: app_17.SteamTestData.WEB_SPACE_HTML_PATH_4,
        app_17.SteamTestData.WEB_SPACE_URL_5: app_17.SteamTestData.WEB_SPACE_HTML_PATH_5,
    })


def _update_html_for_sumatrapdf_app():
    _traverse_dict_to_update({
        app_18.SumatraPdfTestData.WEB_SPACE_URL_2: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_2,
        app_18.SumatraPdfTestData.WEB_SPACE_URL_3: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_3,
        app_18.SumatraPdfTestData.WEB_SPACE_URL_4: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_4,
        app_18.SumatraPdfTestData.WEB_SPACE_URL_5: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_5,
        app_18.SumatraPdfTestData.WEB_SPACE_URL_6: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_6,
        app_18.SumatraPdfTestData.WEB_SPACE_URL_7: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_7,
        app_18.SumatraPdfTestData.WEB_SPACE_URL_8: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_8,
        app_18.SumatraPdfTestData.WEB_SPACE_URL_9: app_18.SumatraPdfTestData.WEB_SPACE_HTML_PATH_9,
    })


if __name__ == '__main__':
    print('Updating HTML/base64 data files...')
    update_html_for_app_tests()
    print('Done.')
