import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class FetchAttributeTestConst:
    TEST_FILE_PATH = r"../resources/gog_galaxy_web_WinMac.base64"


class TestData1:
    def __init__(self):
        self.name = "style"
        self.target = rq.Target(rq.SpaceName.WEB)
        self.set_spaces = rq.SetSpaces()
        test_file = hp.fetch_file(FetchAttributeTestConst.TEST_FILE_PATH).encode('ascii')
        self.set_spaces.web_space = hp.decode_base64(test_file)
        content = b"d2lkdGg6MDtoZWlnaHQ6MDtvdmVyZmxvdzpoaWRkZW47cG9zaXRpb246Zml4ZWQ7dmlzaWJpbGl0eTpoaWRkZW4="
        self.expected_result = hp.decode_base64(content)


class TestData2:
    def __init__(self):
        self.name = "gog-relative-time"
        self.target = rq.Target(rq.SpaceName.WEB)
        self.set_spaces = rq.SetSpaces()
        test_file = hp.fetch_file(FetchAttributeTestConst.TEST_FILE_PATH).encode('ascii')
        self.set_spaces.web_space = hp.decode_base64(test_file)
        content = b"e3sgOjpub3RpZmljYXRpb24uY3JlYXRpb25UaW1lc3RhbXAgfX0="
        self.expected_result = hp.decode_base64(content)


class TestData3:
    def __init__(self):
        self.name = "ng-hide"
        self.target = rq.Target(rq.SpaceName.WEB)
        self.set_spaces = rq.SetSpaces()
        test_file = hp.fetch_file(FetchAttributeTestConst.TEST_FILE_PATH).encode('ascii')
        self.set_spaces.web_space = hp.decode_base64(test_file)
        content = b'cHJvZHVjdC5jdXN0b21BdHRyaWJ1dGVzLmN1c3RvbVByaWNlQnV0dG9uVmFyaWFudCA9PSAnam9pbic='
        self.expected_result = hp.decode_base64(content)


class FetchAttributeTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = tr.FetchAttribute(dt.name)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = tr.FetchAttribute(dt.name)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_3(self):
        # given
        dt = TestData3()
        trigger = tr.FetchAttribute(dt.name)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_json(self):
        # given
        trigger = tr.FetchAttribute(hp.decode_base64(b'YXR0ciI='))
        jstr = b'ewoJCSJ0cmlnZ2VyX3R5cGUiOiAiZmV0Y2hfYXR0cmlidXRlX3RyaWdnZXIiLAoJCSJhdHRyX25hbWUiOiAiYXR0clwiIgp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = trigger.to_json()
        obj = tr.FetchAttribute.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(trigger.compare(obj), obj.compare(obj))


if __name__ == '__main__':
    unittest.main()
