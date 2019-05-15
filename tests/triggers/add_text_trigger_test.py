import unittest
import helpers as hp
import triggers as tr


class AddTextTriggerTestCase(unittest.TestCase):
    def test_json(self):
        # given
        trigger = tr.AddText(hp.decode_base64(b'YQ=='), hp.decode_base64(b'YWI='))
        jstr = b'ewoJCSJ0cmlnZ2VyX3R5cGUiOiAiYWRkX3RleHRfdHJpZ2dlciIsCgkJInByZXBlbmQiOiAiYSIsCgkJImFwcGVuZCI6ICJhY' \
               b'iIKfQ=='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = trigger.to_json()
        obj = tr.AddText.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(trigger.compare(obj), obj.compare(obj))


if __name__ == '__main__':
    unittest.main()
