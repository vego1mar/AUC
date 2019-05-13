import unittest
import helpers as hp
import requesting as rq


class TargetTestCase(unittest.TestCase):
    def test_json(self):
        # given
        str = b'ewoJCSJzZXRfbmFtZSI6ICJXRUIiLAoJCSJpc19nYXRoZXJpbmdfcmVxdWVzdCI6IHRydWUsCgkJImNvbGxlY3RpYmxlX25hbWU' \
              b'iOiAicmFuZG9tIgp9'
        expected_json_str = hp.decode_base64(str)
        target = rq.Target(rq.SpaceName.WEB, True, 'random')

        # when
        json_str = target.to_json()
        obj = rq.Target.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(target.compare(obj), obj.compare(obj))


if __name__ == '__main__':
    unittest.main()
