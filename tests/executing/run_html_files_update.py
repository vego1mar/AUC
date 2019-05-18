import tests.executing.app_bethesda_net_launcher_test as app_1
import tests.executing.app_blizzard_battle_net_test as app_2
import tests.executing.app_driver_booster_test as app_3
import tests.executing.app_dvdfab_virtual_drive_test as app_4
import tests.executing.app_epic_games_launcher_test as app_5
import tests.executing.app_gog_galaxy_test as app_6
import tests.executing.app_google_chrome_test as app_7
import helpers as hp


def update_html_for_app_tests():
    _update_html_for_bethesda_net_launcher_app()
    _update_html_for_blizzard_battle_net_app()
    _update_html_for_driver_booster_app()


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


if __name__ == '__main__':
    print('Updating HTML/base64 data files...')
    # update_html_for_app_tests()
    _update_html_for_google_chrome_app()
    print('Done.')
