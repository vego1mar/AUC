import unittest
import helpers as hp
import triggers as tr


class SelectElementTriggerTestCase(unittest.TestCase):
    def test_json(self):
        # given
        trigger = tr.SelectElement(10100101)
        jstr = b'ewoJCSJ0cmlnZ2VyX3R5cGUiOiAic2VsZWN0X2VsZW1lbnRfdHJpZ2dlciIsCgkJInBvc2l0aW9uIjogMTAxMDAxMDEKfQ=='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = trigger.to_json()
        obj = tr.SelectElement.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(trigger.compare(obj), obj.compare(obj))


if __name__ == '__main__':
    unittest.main()
