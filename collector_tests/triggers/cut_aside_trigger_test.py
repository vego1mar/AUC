import unittest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.requesting import SetSpaces
from collector.triggers import CutAside
from collector.helpers import decode_base64


class TestData:
    def __init__(self):
        self.left = int()
        self.right = int()
        self.target = Target(SpaceName.WORK)
        self.set_spaces = SetSpaces()
        self.set_spaces.work_space = str()
        self.expected_result = str()


class CutAsideTriggerTest(unittest.TestCase):
    def test_both_side_cutting(self):
        # given
        dt = TestData()
        dt.left = 11
        dt.right = 11
        dt.set_spaces.work_space = decode_base64(b'YcSFYmPEh2RlxJlmZ2hpamtsxYJtbm/Ds3BxcnPFm3R1dnd4eXrFvMW6')
        dt.expected_result = decode_base64(b'aWprbMWCbW5vw7NwcXI=')
        trigger = CutAside(dt.left, dt.right)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_left_side_cutting(self):
        # given
        dt = TestData()
        dt.left = 55
        dt.right = 0
        work_space = b'RXN0IGxvcmVtIGlwc3VtIGRvbG9yIHNpdCBhbWV0IGNvbnNlY3RldHVyIGFkaXBpc2Npbmcu'
        dt.set_spaces.work_space = decode_base64(work_space)
        dt.expected_result = str()
        trigger = CutAside(dt.left, dt.right)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_right_side_cutting(self):
        # given
        dt = TestData()
        dt.left = 0
        dt.right = 7
        dt.set_spaces.work_space = decode_base64(b'RG9sb3IgbWFnbmEgZWdldCBlc3QgbG9yZW0gaXBzdW0gZG9sb3Iu')
        dt.expected_result = decode_base64(b'RG9sb3IgbWFnbmEgZWdldCBlc3QgbG9yZW0gaXBzdW0=')
        trigger = CutAside(dt.left, dt.right)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_both_side_cutting_where_there_is_nothing_to_return(self):
        # given
        dt = TestData()
        dt.left = 33
        dt.right = 21
        dt.set_spaces.work_space = decode_base64(b'THVjdHVzIGFjY3Vtc2FuIHRvcnRvciBwb3N1ZXJlIGFjIHV0IGNvbnNlcXVhdC4=')
        dt.expected_result = str()
        trigger = CutAside(dt.left, dt.right)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())


if __name__ == '__main__':
    unittest.main()
