import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class TestData1:
    def __init__(self):
        self.regex = r"[\d]+.[\d]+.[\d]+.[\d]+"
        self.target = rq.Target(rq.SpaceName.WORK)
        self.set_spaces = rq.SetSpaces()
        string = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS9jbGll' \
                 b'bnQvZ2FsYXh5X2NsaWVudF8xLjIuNTQuMjcucGtn'
        self.set_spaces.work_space = hp.decode_base64(string)
        match = b'MS4yLjU0LjI3'
        self.expected_result = hp.decode_base64(match)


class TestData2:
    def __init__(self):
        self.regex = r"[\d]+_[\d]+_[\d]+_[\d]+"
        self.target = rq.Target(rq.SpaceName.WORK)
        self.set_spaces = rq.SetSpaces()
        string = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS9jbGll' \
                 b'bnQvZ2FsYXh5X2NsaWVudF8xLjIuNTQuMjcucGtn'
        self.set_spaces.work_space = hp.decode_base64(string)
        self.expected_result = r""


class GetRegexMatchTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = tr.GetRegexMatch(dt.regex)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = tr.GetRegexMatch(dt.regex)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_json(self):
        # given
        trigger = tr.GetRegexMatch(hp.decode_base64(b'XlswLTldK1tcZF0rID9bLixdKyQ='))
        expected_json_str = hp.decode_base64(b'ewoJCSJwYXR0ZXJuIjogIl5bMC05XStbXFxkXSsgP1suLF0rJCIKfQ==')

        # when
        json_str = trigger.to_json()
        obj = tr.GetRegexMatch.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertEqual(trigger.__dict__, obj.__dict__)


if __name__ == '__main__':
    unittest.main()
