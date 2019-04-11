import unittest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.requesting import SetSpaces
from collector.triggers import FetchAttribute
from collector.helpers import fetch_file
from collector.helpers import decode_base64

TEST_FILE_PATH = r"../resources/gog_galaxy_web_1.base64"
TEST_FILE_DATA = fetch_file(TEST_FILE_PATH)


class TestData1:
    def __init__(self):
        self.name = "style"
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = TEST_FILE_DATA
        content = b"d2lkdGg6MDtoZWlnaHQ6MDtvdmVyZmxvdzpoaWRkZW47cG9zaXRpb246Zml4ZWQ7dmlzaWJpbGl0eTpoaWRkZW4="
        self.expected_result = decode_base64(content)


class TestData2:
    def __init__(self):
        self.name = "gog-relative-time"
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = TEST_FILE_DATA
        content = b"e3sgOjpub3RpZmljYXRpb24uY3JlYXRpb25UaW1lc3RhbXAgfX0="
        self.expected_result = decode_base64(content)


class TestData3:
    def __init__(self):
        self.name = " d"
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = TEST_FILE_DATA
        content = b'TTMwLjIsNy41aC0zLjRjLTAuNSwwLTAuOS0wLjMtMS4yLTAuN2wtMi0zLjdjLTAuMi0wLjQtMC43LTAuNy0xLjItMC43aC05' \
                  b'LjlcbicsICdcdFx0Yy0wLjUsMC0wLjksMC4zLTEuMiwwLjdsLTIsMy43QzkuMiw3LjIsOC43LDcuNSw4LjMsNy41SDQuOGMt' \
                  b'Mi4zLDAtNC4zLDEuNS00LjMsNFYyOGMwLDIuNSwxLjksNC41LDQuMyw0LjVoMjUuNVxuJywgJ1x0XHRjMi4zLDAsNC4yLTIs' \
                  b'NC4yLTQuNVYxMS41QzM0LjUsOS4xLDMyLjYsNy41LDMwLjIsNy41eiBNMzIuNSwyOS4xYzAsMC44LTAuNiwxLjUtMS40LDEu' \
                  b'NUgzLjljLTAuOCwwLTEuNC0wLjctMS40LTEuNVYxMVxuJywgJ1x0XHRjMC0wLjgsMC42LTEuNSwxLjQtMS41aDUuNWMwLjUs' \
                  b'MCwwLjktMC4zLDEuMi0wLjdsMi0zLjNjMC4yLTAuNCwwLjctMC45LDEuMi0wLjloNy42YzAuNSwwLDAuOSwwLjQsMS4yLDAu' \
                  b'OGwxLjgsMy41XG4nLCAnXHRcdGMwLjIsMC40LDAuNywwLjcsMS4yLDAuN2g1LjdjMC44LDAsMS40LDAuNywxLjQsMS41VjI5' \
                  b'LjF6IE0xNy41LDEzLjVjLTMuMywwLTYsMi43LTYsNmMwLDMuMywyLjcsNiw2LDZzNi0yLjcsNi02XG4nLCAnXHRcdEMyMy41' \
                  b'LDE2LjIsMjAuOCwxMy41LDE3LjUsMTMuNXogTTE3LjUsMjMuNWMtMi4yLDAtNC0xLjgtNC00YzAtMi4yLDEuOC00LDQtNHM0' \
                  b'LDEuOCw0LDRDMjEuNSwyMS43LDE5LjcsMjMuNSwxNy41LDIzLjV6'
        self.expected_result = decode_base64(content)


class FetchAttributeTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = FetchAttribute(dt.name)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = FetchAttribute(dt.name)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_3(self):
        # given
        dt = TestData3()
        trigger = FetchAttribute(dt.name)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())


if __name__ == '__main__':
    unittest.main()
