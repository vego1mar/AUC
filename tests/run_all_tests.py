from tests.executing.app_bethesda_net_launcher_test import *
from tests.executing.app_blizzard_battle_net_test import *
from tests.executing.app_driver_booster_test import *
from tests.executing.app_dvdfab_virtual_drive_test import *
from tests.executing.app_epic_games_launcher_test import *
from tests.executing.app_gog_galaxy_test import *
from tests.executing.app_google_chrome_test import *
from tests.executing.app_hashtab_test import *
from tests.executing.app_irfanview_test import *
from tests.executing.app_jetclean_test import *
from tests.executing.app_keepass_test import *
from tests.executing.app_malware_fighter_test import *
from tests.executing.app_origin_test import *
from tests.executing.app_potplayer_test import *
from tests.executing.app_smart_defrag_test import *
from tests.executing.app_speccy_test import *
from tests.executing.app_steam_test import *
from tests.executing.app_sumatrapdf_test import *
from tests.executing.chain_request_execution_test import *
from tests.executing.execution_order_entry_test import *
from tests.executing.info_collector_test import *
from tests.helpers.base64_test import *
from tests.helpers.remove_characters_test import *
from tests.requesting.invocation_request_test import *
from tests.requesting.target_test import *
from tests.triggers.cut_aside_trigger_test import *
from tests.triggers.delete_trigger_test import *
from tests.triggers.fetch_attribute_trigger_test import *
from tests.triggers.find_next_trigger_test import *
from tests.triggers.find_trigger_test import *
from tests.triggers.get_regex_match_trigger_test import *
from tests.triggers.get_subset_trigger_test import *
from tests.triggers.retrieve_tags_trigger_test import *


def adjust_file_paths():
    BethesdaNetLauncherTestData.WEB_SPACE_HTML_PATH_2 = BethesdaNetLauncherTestData.WEB_SPACE_HTML_PATH_2[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_1 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_1[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_4 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_4[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_5 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_5[1:]
    DriverBoosterTestData.WEB_SPACE_HTML_PATH_2 = DriverBoosterTestData.WEB_SPACE_HTML_PATH_2[1:]
    DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_1 = DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_1[1:]
    DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_2 = DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_2[1:]
    EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_1 = EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_1[1:]
    EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_2 = EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_2[1:]
    GOGGalaxyTestData.WEB_SPACE_HTML_PATH_1 = GOGGalaxyTestData.WEB_SPACE_HTML_PATH_1[1:]
    GOGGalaxyTestData.WEB_SPACE_HTML_PATH_2 = GOGGalaxyTestData.WEB_SPACE_HTML_PATH_2[1:]
    GoogleChromeTestData.WEB_SPACE_HTML_PATH_2 = GoogleChromeTestData.WEB_SPACE_HTML_PATH_2[1:]
    GoogleChromeTestData.WEB_SPACE_HTML_PATH_3 = GoogleChromeTestData.WEB_SPACE_HTML_PATH_3[1:]
    GoogleChromeTestData.WEB_SPACE_HTML_PATH_4 = GoogleChromeTestData.WEB_SPACE_HTML_PATH_4[1:]
    GoogleChromeTestData.WEB_SPACE_HTML_PATH_5 = GoogleChromeTestData.WEB_SPACE_HTML_PATH_5[1:]
    HashTabTestData.WEB_SPACE_HTML_PATH_1 = HashTabTestData.WEB_SPACE_HTML_PATH_1[1:]
    HashTabTestData.WEB_SPACE_HTML_PATH_2 = HashTabTestData.WEB_SPACE_HTML_PATH_2[1:]
    IrfanViewTestData.WEB_SPACE_HTML_PATH_2 = IrfanViewTestData.WEB_SPACE_HTML_PATH_2[1:]
    IrfanViewTestData.WEB_SPACE_HTML_PATH_3 = IrfanViewTestData.WEB_SPACE_HTML_PATH_3[1:]
    IrfanViewTestData.WEB_SPACE_HTML_PATH_4 = IrfanViewTestData.WEB_SPACE_HTML_PATH_4[1:]
    IrfanViewTestData.WEB_SPACE_HTML_PATH_5 = IrfanViewTestData.WEB_SPACE_HTML_PATH_5[1:]
    JetCleanTestData.WEB_SPACE_HTML_PATH_2 = JetCleanTestData.WEB_SPACE_HTML_PATH_2[1:]
    JetCleanTestData.WEB_SPACE_HTML_PATH_3 = JetCleanTestData.WEB_SPACE_HTML_PATH_3[1:]
    JetCleanTestData.WEB_SPACE_HTML_PATH_4 = JetCleanTestData.WEB_SPACE_HTML_PATH_4[1:]
    KeePassTestData.WEB_SPACE_HTML_PATH_2 = KeePassTestData.WEB_SPACE_HTML_PATH_2[1:]
    KeePassTestData.WEB_SPACE_HTML_PATH_3 = KeePassTestData.WEB_SPACE_HTML_PATH_3[1:]
    MalwareFighterTestData.WEB_SPACE_HTML_PATH_2 = MalwareFighterTestData.WEB_SPACE_HTML_PATH_2[1:]
    OriginTestData.WEB_SPACE_HTML_PATH_1 = OriginTestData.WEB_SPACE_HTML_PATH_1[1:]
    OriginTestData.WEB_SPACE_HTML_PATH_2 = OriginTestData.WEB_SPACE_HTML_PATH_2[1:]
    PotPlayerTestData.WEB_SPACE_HTML_PATH_1 = PotPlayerTestData.WEB_SPACE_HTML_PATH_1[1:]
    PotPlayerTestData.WEB_SPACE_HTML_PATH_2 = PotPlayerTestData.WEB_SPACE_HTML_PATH_2[1:]
    SmartDefragTestData.WEB_SPACE_HTML_PATH_2 = SmartDefragTestData.WEB_SPACE_HTML_PATH_2[1:]
    SmartDefragTestData.WEB_SPACE_HTML_PATH_3 = SmartDefragTestData.WEB_SPACE_HTML_PATH_3[1:]
    SpeccyTestData.WEB_SPACE_HTML_PATH_2 = SpeccyTestData.WEB_SPACE_HTML_PATH_2[1:]
    SpeccyTestData.WEB_SPACE_HTML_PATH_3 = SpeccyTestData.WEB_SPACE_HTML_PATH_3[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_1 = SteamTestData.WEB_SPACE_HTML_PATH_1[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_2 = SteamTestData.WEB_SPACE_HTML_PATH_2[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_3 = SteamTestData.WEB_SPACE_HTML_PATH_3[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_4 = SteamTestData.WEB_SPACE_HTML_PATH_4[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_5 = SteamTestData.WEB_SPACE_HTML_PATH_5[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_2 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_2[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_3 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_3[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_4 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_4[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_5 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_5[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_6 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_6[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_7 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_7[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_8 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_8[1:]
    SumatraPdfTestData.WEB_SPACE_HTML_PATH_9 = SumatraPdfTestData.WEB_SPACE_HTML_PATH_9[1:]
    ChainRequestExecutionTestData.WEB_PAGE_1_FILE_NAME = ChainRequestExecutionTestData.WEB_PAGE_1_FILE_NAME[1:]
    InfoCollectorTestData.FILE_NAME_TO_LOAD = InfoCollectorTestData.FILE_NAME_TO_LOAD[1:]
    FetchAttributeTestConst.TEST_FILE_PATH = FetchAttributeTestConst.TEST_FILE_PATH[1:]
    FindNextTriggerConst.TEST_FILE = FindNextTriggerConst.TEST_FILE[1:]
    RetrieveTagsConst.TEST_FILE_PATH = RetrieveTagsConst.TEST_FILE_PATH[1:]


if __name__ == '__main__':
    adjust_file_paths()
    unittest.main()
