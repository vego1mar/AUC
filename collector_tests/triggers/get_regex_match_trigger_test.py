import unittest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.requesting import SetSpaces
from collector.triggers import GetRegexMatch
from collector.helpers import decode_base64


class TestData1:
    def __init__(self):
        self.regex = r"[\d]+.[\d]+.[\d]+.[\d]+"
        self.target = Target(SpaceName.WORK)
        self.set_spaces = SetSpaces()
        string = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS9jbGll' \
                 b'bnQvZ2FsYXh5X2NsaWVudF8xLjIuNTQuMjcucGtn'
        self.set_spaces.work_space = decode_base64(string)
        match = b'MS4yLjU0LjI3'
        self.expected_result = decode_base64(match)


class TestData2:
    def __init__(self):
        self.regex = r"[\d]+_[\d]+_[\d]+_[\d]+"
        self.target = Target(SpaceName.WORK)
        self.set_spaces = SetSpaces()
        string = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS9jbGll' \
                 b'bnQvZ2FsYXh5X2NsaWVudF8xLjIuNTQuMjcucGtn'
        self.set_spaces.work_space = decode_base64(string)
        self.expected_result = r""


class GetRegexMatchTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = GetRegexMatch(dt.regex)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = GetRegexMatch(dt.regex)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())


if __name__ == '__main__':
    unittest.main()
