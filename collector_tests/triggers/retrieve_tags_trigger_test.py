import unittest
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.requesting import SetSpaces
from collector.helpers import fetch_file
from collector.helpers import decode_base64

TEST_FILE_PATH = r"../resources/gog_galaxy_web_1.base64"
TEST_FILE_DATA = fetch_file(TEST_FILE_PATH)


class TestData1():
    def __init__(self):
        self.tag_name = "p"
        self.tag_type = TagType.SIMPLE
        self.amount = 1
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = TEST_FILE_DATA
        self._provide_expected_tag()
        self.expected_result = [self._expected_tag]

    def _provide_expected_tag(self):
        p_tag = b'PHA+XG4nLCAnICAgICAgICAgICAgICAgICAgICBTdHdvcnpvbmUgeiBwYXNqxIUgdyBXYXJzemF3aWUsIHN0b2xpY3kgUG9sc2' \
                b'tpLiBQb2R6aWVsIHNpxJkgeiBuYW1pIHN3b2ltaSA8YSBocmVmPSJodHRwczovL3d3dy5nb2cuY29tL3dpc2hsaXN0L2dhbGF4' \
                b'eSI+PHN0cm9uZz51d2FnYW1pPC9zdHJvbmc+PC9hPiBsdWIgemfFgm/FmyA8YSBocmVmPSJodHRwOi8vbWFudGlzLmdvZy5jb2' \
                b'0vIj48c3Ryb25nPmLFgsSZZHk8L3N0cm9uZz48L2E+IGkgcG9tw7PFvCBuYW0gamVzemN6ZSBiYXJkemllaiB1bGVwc3p5xIcg' \
                b'R09HIEdhbGF4eS5cbicsICcgICAgICAgICAgICAgICAgPC9wPg=='
        self._expected_tag = decode_base64(p_tag)


class TestData2():
    def __init__(self):
        self.tag_name = "g"
        self.tag_type = TagType.ATTRIBUTED
        self.amount = 2
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = TEST_FILE_DATA
        g_tags = b'Wyc8ZyBpZD0iY2FtZXJhLV94MzNfNXB4Ij48cGF0aCBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGNsaXAtcnVsZT0iZXZlbm9kZCIgZ' \
                 b'D0iTTMwLjIsNy41aC0zLjRjLTAuNSwwLTAuOS0wLjMtMS4yLTAuN2wtMi0zLjdjLTAuMi0wLjQtMC43LTAuNy0xLjItMC43aC' \
                 b'05LjlcXG5cJywgXCdcXHRcXHRjLTAuNSwwLTAuOSwwLjMtMS4yLDAuN2wtMiwzLjdDOS4yLDcuMiw4LjcsNy41LDguMyw3LjV' \
                 b'INC44Yy0yLjMsMC00LjMsMS41LTQuMyw0VjI4YzAsMi41LDEuOSw0LjUsNC4zLDQuNWgyNS41XFxuXCcsIFwnXFx0XFx0YzIu' \
                 b'MywwLDQuMi0yLDQuMi00LjVWMTEuNUMzNC41LDkuMSwzMi42LDcuNSwzMC4yLDcuNXogTTMyLjUsMjkuMWMwLDAuOC0wLjYsM' \
                 b'S41LTEuNCwxLjVIMy45Yy0wLjgsMC0xLjQtMC43LTEuNC0xLjVWMTFcXG5cJywgXCdcXHRcXHRjMC0wLjgsMC42LTEuNSwxLj' \
                 b'QtMS41aDUuNWMwLjUsMCwwLjktMC4zLDEuMi0wLjdsMi0zLjNjMC4yLTAuNCwwLjctMC45LDEuMi0wLjloNy42YzAuNSwwLDA' \
                 b'uOSwwLjQsMS4yLDAuOGwxLjgsMy41XFxuXCcsIFwnXFx0XFx0YzAuMiwwLjQsMC43LDAuNywxLjIsMC43aDUuN2MwLjgsMCwx' \
                 b'LjQsMC43LDEuNCwxLjVWMjkuMXogTTE3LjUsMTMuNWMtMy4zLDAtNiwyLjctNiw2YzAsMy4zLDIuNyw2LDYsNnM2LTIuNyw2L' \
                 b'TZcXG5cJywgXCdcXHRcXHRDMjMuNSwxNi4yLDIwLjgsMTMuNSwxNy41LDEzLjV6IE0xNy41LDIzLjVjLTIuMiwwLTQtMS44LT' \
                 b'QtNGMwLTIuMiwxLjgtNCw0LTRzNCwxLjgsNCw0QzIxLjUsMjEuNywxOS43LDIzLjUsMTcuNSwyMy41eiIvPjwvZz4nLCAnPGc' \
                 b'gaWQ9ImNoYXQtX3gzM181cHgiPjxwYXRoIGQ9Ik0zNC40LDIzLjlMMzIuMSwxOGMwLjctMS40LDEuNS0yLjksMS41LTQuNWMw' \
                 b'LTYuMS00LjctMTEtMTEtMTFjLTQuOSwwLTguMywyLjYtOS45LDdDNi43LDkuOSwxLjQsMTUsMS40LDIwLjhcXG5cJywgXCdcX' \
                 b'HRcXHRjMCwxLjYsMC45LDMuMSwxLjUsNC41bC0yLjQsNS45Yy0wLjEsMC4zLDAsMC45LDAuMiwxLjFjMC4yLDAuMiwwLjQsMC' \
                 b'4zLDAuNiwwLjNjMC4xLDAsMC4yLDAsMC4zLTAuMWw1LjItMS42XFxuXCcsIFwnXFx0XFx0YzEuOSwxLjMsNC4yLDEuNyw2LjY' \
                 b'sMS43YzUsMCw5LjMtMy44LDEwLjktOC4xYzIuMS0wLjEsMiwwLjIsMy44LTAuOWw1LjIsMS42YzAuMSwwLjEsMC4yLDAuMSww' \
                 b'LjMsMC4xYzAuMiwwLDAuNC0wLjEsMC42LTAuM1xcblwnLCBcJ1xcdFxcdEMzNC41LDI0LjcsMzQuNiwyNC4zLDM0LjQsMjMuO' \
                 b'XogTTEzLjUsMzAuNmMtMi4yLDAtNC4zLTAuNC02LjEtMS43Yy0wLjEtMC4xLTAuMy0wLjEtMC40LTAuMWMtMC4xLDAtMC4yLD' \
                 b'AtMC4zLDAuMUwzLDI5LjhsMS44LTQuMlxcblwnLCBcJ1xcdFxcdGMwLjEtMC4zLDAuMS0wLjYsMC0wLjhjLTAuNy0xLjMtMS4' \
                 b'yLTIuNi0xLjItNGMwLTUsNC41LTkuMywxMC05LjNjNS41LDAsOSw0LjMsOSw5LjNDMjIuNSwyNS44LDE5LDMwLjYsMTMuNSwz' \
                 b'MC42eiBNMjguNCwyMS41XFxuXCcsIFwnXFx0XFx0Yy0wLjMtMC4xLTAuNS0wLjEtMC44LDAuMWMtMS40LDEtMSwwLjgtMi43L' \
                 b'DFjMC4xLTAuNi0wLjQtMS4xLTAuNC0xLjdjMC01LjgtNC4xLTEwLjktMTAtMTEuM2MxLjYtMy4zLDQuMS00LjksOC4xLTQuOV' \
                 b'xcblwnLCBcJ1xcdFxcdGM1LjUsMCw5LDQsOSw5YzAsMS40LTAuNywyLjctMS40LDRjLTAuMSwwLjMtMC4yLDAuNiwwLDAuOGw' \
                 b'yLDQuMkwyOC40LDIxLjV6Ii8+PC9nPidd'
        self.expected_result = decode_base64(g_tags)


class TestData3():
    def __init__(self):
        self.tag_name = "link"
        self.tag_type = TagType.META
        self.amount = 3
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = TEST_FILE_DATA
        link_tags = b'Wyc8bGluayByZWw9InNob3J0Y3V0IGljb24iIGhyZWY9Ii9mYXZpY29uLmljbz8zIj4nLCAnPGxpbmsgcmVsPSJhcHBsZS' \
                    b'10b3VjaC1pY29uIiBocmVmPSIvYXBwbGUtdG91Y2gtaWNvbi01N3g1Ny5wbmciIHNpemVzPSI1N3g1NyI+JywgJzxsaW5r' \
                    b'IHJlbD0iYXBwbGUtdG91Y2gtaWNvbiIgaHJlZj0iL2FwcGxlLXRvdWNoLWljb24tNzJ4NzIucG5nIiBzaXplcz0iNzJ4Nz' \
                    b'IiPidd'
        self.expected_result = decode_base64(link_tags)


class RetrieveTagsTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = RetrieveTags(dt.tag_name, dt.tag_type, dt.amount)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = RetrieveTags(dt.tag_name, dt.tag_type, dt.amount)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, str(trigger.get_result()))

    def test_invoke_3(self):
        # given
        dt = TestData3()
        trigger = RetrieveTags(dt.tag_name, dt.tag_type, dt.amount)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, str(trigger.get_result()))


if __name__ == '__main__':
    unittest.main()
