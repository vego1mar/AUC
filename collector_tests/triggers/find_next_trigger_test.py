import unittest
from collector.triggers import FindNext
from collector.requesting import SetSpaces
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.helpers import fetch_file


class TestData1:
    TEST_FILE = r"../resources/lorem_ipsum.txt"

    def __init__(self):
        self.text_to_find = "integer"
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = fetch_file(self.TEST_FILE)
        self.target = Target(SpaceName.WEB)
        self.expected_result = r"integer.\n']"


class TestData2:
    def __init__(self):
        self.text_to_find = "b"
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = "aba"
        self.target = Target(SpaceName.WEB)
        self.expected_result = ""


class TestData3:
    def __init__(self):
        self.text_to_find = "c"
        self.set_spaces = SetSpaces()
        self.set_spaces.web_space = "baba"
        self.target = Target(SpaceName.WEB)
        self.expected_result = ""


class FindNextTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = FindNext(dt.text_to_find)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = FindNext(dt.text_to_find)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())
        self.assertLogs()

    def test_invoke_3(self):
        # given
        dt = TestData3()
        trigger = FindNext(dt.text_to_find)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())
        self.assertLogs()


if __name__ == '__main__':
    unittest.main()
