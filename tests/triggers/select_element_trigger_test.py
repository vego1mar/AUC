import unittest
import helpers as hp
import triggers as tr


class SelectElementTriggerTestCase(unittest.TestCase):
    def test_json(self):
        # given
        trigger = tr.SelectElement(0)
        expected_json_str = hp.decode_base64(b'ewoJCSJwb3NpdGlvbiI6IDAKfQ==')

        # when
        json_str = trigger.to_json()
        obj = tr.SelectElement.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertEqual(trigger.__dict__, obj.__dict__)


if __name__ == '__main__':
    unittest.main()
