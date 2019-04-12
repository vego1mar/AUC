import unittest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.requesting import SetSpaces
from collector.triggers import FetchAttribute
from collector.helpers import fetch_file
from collector.helpers import decode_base64


class FetchAttributeTestConst:
    TEST_FILE_PATH = r"../resources/gog_galaxy_web_WinMac.base64"


class TestData1:
    def __init__(self):
        self.name = "style"
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = decode_base64(fetch_file(FetchAttributeTestConst.TEST_FILE_PATH).encode('ascii'))
        content = b"d2lkdGg6MDtoZWlnaHQ6MDtvdmVyZmxvdzpoaWRkZW47cG9zaXRpb246Zml4ZWQ7dmlzaWJpbGl0eTpoaWRkZW4="
        self.expected_result = decode_base64(content)


class TestData2:
    def __init__(self):
        self.name = "gog-relative-time"
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = decode_base64(fetch_file(FetchAttributeTestConst.TEST_FILE_PATH).encode('ascii'))
        content = b"e3sgOjpub3RpZmljYXRpb24uY3JlYXRpb25UaW1lc3RhbXAgfX0="
        self.expected_result = decode_base64(content)


class TestData3:
    def __init__(self):
        self.name = "ng-hide"
        self.target = Target(SpaceName.WEB)
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = decode_base64(fetch_file(FetchAttributeTestConst.TEST_FILE_PATH).encode('ascii'))
        content = b'cHJvZHVjdC5jdXN0b21BdHRyaWJ1dGVzLmN1c3RvbVByaWNlQnV0dG9uVmFyaWFudCA9PSAnam9pbic='
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
