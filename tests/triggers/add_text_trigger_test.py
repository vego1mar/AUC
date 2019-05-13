import unittest
import helpers as hp
import triggers as tr


class AddTextTriggerTestCase(unittest.TestCase):
    def test_json(self):
        # given
        trigger = tr.AddText('a', 'ab')
        expected_json_str = hp.decode_base64(b'ewoJCSJwcmVwZW5kIjogImEiLAoJCSJhcHBlbmQiOiAiYWIiCn0=')

        # when
        json_str = trigger.to_json()
        obj = tr.AddText.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertEqual(trigger.__dict__, obj.__dict__)


if __name__ == '__main__':
    unittest.main()
