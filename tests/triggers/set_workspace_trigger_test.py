import unittest
import triggers as tr


class SetWorkspaceTriggerTestCase(unittest.TestCase):
    def test_json_encode(self):
        # given
        trigger = tr.SetWorkspace("Foxy brown fox.")
        expected = '{\n "text": "Foxy brown fox."\n}'

        # when
        result = tr.SetWorkspace.encode(trigger, trigger)

        # then
        self.assertEqual(expected, result)


if __name__ == '__main__':
    unittest.main()
