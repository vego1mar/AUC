from collector.tests.executing.app_bethesda_net_launcher_test import *
from collector.tests.executing.app_blizzard_battle_net_test import *
from collector.tests.executing.app_driver_booster_test import *
from collector.tests.executing.app_epic_games_launcher_test import *
from collector.tests.executing.app_gog_galaxy_test import *
from collector.tests.executing.app_google_chrome_test import *
from collector.tests.executing.app_hashtab_test import *
from collector.tests.executing.app_malware_fighter_test import *
from collector.tests.executing.app_origin_test import *
from collector.tests.executing.app_steam_test import *
from collector.tests.executing.chain_request_execution_test import *
from collector.tests.executing.info_collector_test import *
from collector.tests.helpers.base64_test import *
from collector.tests.helpers.remove_characters_test import *
from collector.tests.triggers.cut_aside_trigger_test import *
from collector.tests.triggers.delete_trigger_test import *
from collector.tests.triggers.fetch_attribute_trigger_test import *
from collector.tests.triggers.find_next_trigger_test import *
from collector.tests.triggers.find_trigger_test import *
from collector.tests.triggers.get_regex_match_trigger_test import *
from collector.tests.triggers.get_subset_trigger_test import *
from collector.tests.triggers.retrieve_tags_trigger_test import *


def adjust_file_paths():
    BethesdaNetLauncherTestData.WEB_SPACE_HTML_PATH_2 = BethesdaNetLauncherTestData.WEB_SPACE_HTML_PATH_2[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_1 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_1[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2[1:]
    BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3 = BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3[1:]
    DriverBoosterTestData.WEB_SPACE_HTML_PATH_2 = DriverBoosterTestData.WEB_SPACE_HTML_PATH_2[1:]
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
    MalwareFighterTestData.WEB_SPACE_HTML_PATH_2 = MalwareFighterTestData.WEB_SPACE_HTML_PATH_2[1:]
    OriginTestData.WEB_SPACE_HTML_PATH_1 = OriginTestData.WEB_SPACE_HTML_PATH_1[1:]
    OriginTestData.WEB_SPACE_HTML_PATH_2 = OriginTestData.WEB_SPACE_HTML_PATH_2[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_1 = SteamTestData.WEB_SPACE_HTML_PATH_1[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_2 = SteamTestData.WEB_SPACE_HTML_PATH_2[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_3 = SteamTestData.WEB_SPACE_HTML_PATH_3[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_4 = SteamTestData.WEB_SPACE_HTML_PATH_4[1:]
    SteamTestData.WEB_SPACE_HTML_PATH_5 = SteamTestData.WEB_SPACE_HTML_PATH_5[1:]
    ChainRequestExecutionTestData.WEB_PAGE_1_FILE_NAME = ChainRequestExecutionTestData.WEB_PAGE_1_FILE_NAME[1:]
    InfoCollectorTestData.FILE_NAME_TO_LOAD = InfoCollectorTestData.FILE_NAME_TO_LOAD[1:]
    FetchAttributeTestConst.TEST_FILE_PATH = FetchAttributeTestConst.TEST_FILE_PATH[1:]
    FindNextTriggerConst.TEST_FILE = FindNextTriggerConst.TEST_FILE[1:]
    RetrieveTagsConst.TEST_FILE_PATH = RetrieveTagsConst.TEST_FILE_PATH[1:]


def print_info():
    def print_module_functions(mod_name, mod_dict):
        functions_names = [name for name in mod_dict if not name.startswith('__')]
        print(mod_name + '[', end='')

        for name in functions_names:
            print(name + '()', end=', ')

        print(']')

    print_module_functions(BethesdaNetLauncherTest.__name__, BethesdaNetLauncherTest.__dict__)
    print_module_functions(ActivisionBlizzardBattleNetTest.__name__, ActivisionBlizzardBattleNetTest.__dict__)
    print_module_functions(DriverBoosterTest.__name__, DriverBoosterTest.__dict__)
    print_module_functions(EpicGamesLauncherTest.__name__, EpicGamesLauncherTest.__dict__)
    print_module_functions(GogGalaxyTest.__name__, GogGalaxyTest.__dict__)
    print_module_functions(GoogleChromeTest.__name__, GoogleChromeTest.__dict__)
    print_module_functions(HashTabTest.__name__, HashTabTest.__dict__)
    print_module_functions(MalwareFighterTest.__name__, MalwareFighterTest.__dict__)
    print_module_functions(ElectronicArtsOriginTest.__name__, ElectronicArtsOriginTest.__dict__)
    print_module_functions(SteamClientBootstrapperTest.__name__, SteamClientBootstrapperTest.__dict__)
    print_module_functions(ChainRequestExecutionTest.__name__, ChainRequestExecutionTest.__dict__)
    print_module_functions(InfoCollectorTest.__name__, InfoCollectorTest.__dict__)
    print_module_functions(Base64Test.__name__, Base64Test.__dict__)
    print_module_functions(RemoveCharactersTest.__name__, RemoveCharactersTest.__dict__)
    print_module_functions(CutAsideTriggerTest.__name__, CutAsideTriggerTest.__dict__)
    print_module_functions(DeleteTriggerTest.__name__, DeleteTriggerTest.__dict__)
    print_module_functions(FetchAttributeTriggerTest.__name__, FetchAttributeTriggerTest.__dict__)
    print_module_functions(FindNextTriggerTest.__name__, FindNextTriggerTest.__dict__)
    print_module_functions(FindTriggerTest.__name__, FindTriggerTest.__dict__)
    print_module_functions(GetRegexMatchTriggerTest.__name__, GetRegexMatchTriggerTest.__dict__)
    print_module_functions(GetSubsetTriggerTest.__name__, GetSubsetTriggerTest.__dict__)
    print_module_functions(RetrieveTagsTriggerTest.__name__, RetrieveTagsTriggerTest.__dict__)


adjust_file_paths()
print_info()
unittest.main()
