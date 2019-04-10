import unittest
from collector.requesting import Target
from collector.requesting import TargetSetName
from collector.requesting import SetSpaces
from collector.triggers import GetSubset
from collector.helpers import decode_base64


class TestData:
    def __init__(self):
        self.begin = str()
        self.end = str()
        self.target = Target(TargetSetName.WORK_SPACE)
        self.set_spaces = SetSpaces()
        self.expected_result = str()


class GetSubsetTriggerTest(unittest.TestCase):
    def test_string_and_character(self):
        # given
        dt = TestData()
        dt.begin = "in"
        dt.end = 's'
        work_space = b'T2RpbyB0ZW1wb3Igb3JjaSBkYXBpYnVzIHVsdHJpY2VzIGluIGlhY3VsaXMgbnVuYyBzZWQgYXVndWU='
        dt.set_spaces.work_space = decode_base64(work_space)
        dt.expected_result = decode_base64(b'aW4gaWFjdWxpcw==')
        trigger = GetSubset(dt.begin, dt.end)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_string_and_string(self):
        # given
        dt = TestData()
        dt.begin = "purus"
        dt.end = "nec"
        work_space = b'TG9ib3J0aXMgbWF0dGlzIGFsaXF1YW0gZmF1Y2lidXMgcHVydXMgaW4gbWFzc2EgdGVtcG9yIG5lYyBmZXVnaWF0Lg=='
        dt.set_spaces.work_space = decode_base64(work_space)
        dt.expected_result = decode_base64(b'cHVydXMgaW4gbWFzc2EgdGVtcG9yIG5lYw==')
        trigger = GetSubset(dt.begin, dt.end)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_character_and_character(self):
        # given
        dt = TestData()
        dt.begin = 'S'
        dt.end = 'r'
        work_space = b'UGxhY2VyYXQgaW4gZWdlc3RhcyBlcmF0IGltcGVyZGlldCBzZWQuIFNlZCBhdWd1ZSBsYWN1cyB2aXZlcnJhIHZpdG' \
                     b'FlIGNvbmd1ZSBldSBjb25zZXF1YXQu'
        dt.set_spaces.work_space = decode_base64(work_space)
        dt.expected_result = decode_base64(b'U2VkIGF1Z3VlIGxhY3VzIHZpdmVy')
        trigger = GetSubset(dt.begin, dt.end)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_intigerify(self):
        # given
        dt = TestData()
        dt.begin = 9
        dt.end = 15
        work_space = b'VmVsaXQgdXQgdG9ydG9yIHByZXRpdW0gdml2ZXJyYSBzdXNwZW5kaXNzZSBwb3RlbnRpIG51bGxhbSBhYy4='
        dt.set_spaces.work_space = decode_base64(work_space)
        dt.expected_result = decode_base64(b'dG9ydG9y')
        trigger = GetSubset(dt.begin, dt.end)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_integer_and_string(self):
        # given
        dt = TestData()
        dt.begin = 0
        dt.end = "abc"
        work_space = b'TnVsbGEgYXQgdm9sdXRwYXQgZGlhbSB1dC4gTG9yZW0gbW9sbGlzIGFsaXF1YW0gdXQgcG9ydHRpdG9yIGxlby4='
        dt.set_spaces.work_space = decode_base64(work_space)
        dt.expected_result = str()
        trigger = GetSubset(dt.begin, dt.end)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())


if __name__ == '__main__':
    unittest.main()
