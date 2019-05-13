import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class FindNextTriggerConst:
    TEST_FILE = r"../resources/lorem_ipsum.txt"


class TestData1:
    def __init__(self):
        self.text_to_find = "integer"
        self.set_spaces = rq.SetSpaces()
        self.set_spaces.web_space = hp.fetch_file(FindNextTriggerConst.TEST_FILE)
        self.target = rq.Target(rq.SpaceName.WEB)
        self.expected_result = r"integer.\n']"


class TestData2:
    def __init__(self):
        self.text_to_find = "b"
        self.set_spaces = rq.SetSpaces()
        self.set_spaces.web_space = "aba"
        self.target = rq.Target(rq.SpaceName.WEB)
        self.expected_result = ""


class TestData3:
    def __init__(self):
        self.text_to_find = "c"
        self.set_spaces = rq.SetSpaces()
        self.set_spaces.web_space = "baba"
        self.target = rq.Target(rq.SpaceName.WEB)
        self.expected_result = ""


class FindNextTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = tr.FindNext(dt.text_to_find)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = tr.FindNext(dt.text_to_find)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())
        self.assertLogs()

    def test_invoke_3(self):
        # given
        dt = TestData3()
        trigger = tr.FindNext(dt.text_to_find)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())
        self.assertLogs()

    def test_json(self):
        # given
        trigger = tr.FindNext(hp.decode_base64(b'c2FtcGxlIi0idGV4dA=='))
        expected_json_str = hp.decode_base64(b'ewoJCSJ0ZXh0IjogInNhbXBsZVwiLVwidGV4dCIKfQ==')

        # when
        json_str = trigger.to_json()
        obj = tr.FindNext.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertEqual(trigger.__dict__, obj.__dict__)


if __name__ == '__main__':
    unittest.main()
