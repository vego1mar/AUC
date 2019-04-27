import unittest
import triggers as tr
import requesting as rq


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


if __name__ == '__main__':
    unittest.main()
