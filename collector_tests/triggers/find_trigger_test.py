import unittest
from collector.triggers import Find
from collector.requesting import Target
from collector.requesting import TargetSetName
from collector.requesting import SetSpaces


class FindTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        target = Target(TargetSetName.WEB_SPACE)
        set_spaces = SetSpaces()
        text_to_find = "luna"
        trigger = Find(text_to_find)
        set_spaces.web_space = "sanguinem-luna-luminarae"
        expected_result = set_spaces.web_space[set_spaces.web_space.index(text_to_find):]

        # when
        trigger.invoke(target, set_spaces)

        # then
        self.assertEqual(trigger.get_result(), expected_result)


if __name__ == '__main__':
    unittest.main()
