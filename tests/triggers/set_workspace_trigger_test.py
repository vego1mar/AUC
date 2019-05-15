import unittest
import helpers as hp
import triggers as tr


class SetWorkspaceTriggerTestCase(unittest.TestCase):
    def test_json(self):
        # given
        trigger = tr.SetWorkspace(hp.decode_base64(b'0KTQh9CU0JjQqdCv'))
        jstr = b'ewoJCSJ0cmlnZ2VyX3R5cGUiOiAic2V0X3dvcmtzcGFjZV90cmlnZ2VyIiwKCQkidGV4dCI6ICJcdTA0MjRcdTA0MDdcdTA0MT' \
               b'RcdTA0MThcdTA0MjlcdTA0MmYiCn0='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = trigger.to_json()
        obj = tr.SetWorkspace.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(trigger.compare(obj), obj.compare(obj))


if __name__ == '__main__':
    unittest.main()
