import unittest
import triggers as tr


class AddTextTriggerTestCase(unittest.TestCase):
    def test_json_encode(self):
        # given
        trigger = tr.AddText("pr", "")
        expected = '{\n "prepend": "pr",\n "append": ""\n}'

        # when
        result = tr.AddText.encode(trigger, trigger)

        # then
        self.assertEqual(expected, result)


if __name__ == '__main__':
    unittest.main()
