import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class FindTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        target = rq.Target(rq.SpaceName.WEB)
        set_spaces = rq.SetSpaces()
        text_to_find = "luna"
        trigger = tr.Find(text_to_find)
        set_spaces.web_space = "sanguinem-luna-luminarae"
        expected_result = set_spaces.web_space[set_spaces.web_space.index(text_to_find):]

        # when
        trigger.invoke(target, set_spaces)

        # then
        self.assertEqual(trigger.get_result(), expected_result)

    def test_json(self):
        # given
        trigger = tr.Find('sample-text')
        jstr = b'ewoJCSJ0cmlnZ2VyX3R5cGUiOiAiZmluZF90cmlnZ2VyIiwKCQkidGV4dCI6ICJzYW1wbGUtdGV4dCIKfQ=='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = trigger.to_json()
        obj = tr.Find.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(trigger.compare(obj), obj.compare(obj))


if __name__ == '__main__':
    unittest.main()
