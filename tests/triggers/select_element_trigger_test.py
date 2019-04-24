import unittest
import triggers as tr


class SelectElementTriggerTestCase(unittest.TestCase):
    def test_json_encode(self):
        # given
        trigger = tr.SelectElement(1)
        expected = '{\n "position": 1\n}'

        # when
        result = tr.SelectElement.encode(trigger, trigger)

        # then
        self.assertEqual(expected, result)


if __name__ == '__main__':
    unittest.main()
