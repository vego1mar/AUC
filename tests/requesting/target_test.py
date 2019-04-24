import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class TargetTestCase(unittest.TestCase):
    def test_to_json(self):
        # given
        set_name_1 = tr.TagType.SIMPLE
        target_1 = rq.Target(set_name_1)
        var_1 = b'ewoJInNldF9uYW1lIjogIldFQiIsCgkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZSwKCSJjb2xsZWN0aWJsZV9uYW1lIj' \
                b'ogImdlbmVyYWwiCn0K'
        expected_json_1 = hp.decode_base64(var_1)
        set_name_2 = tr.TagType.META
        target_2 = rq.Target(set_name_2, True)
        var_2 = b'ewoJInNldF9uYW1lIjogIkxJU1QiLAoJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogdHJ1ZSwKCSJjb2xsZWN0aWJsZV9uYW1lIj' \
                b'ogImdlbmVyYWwiCn0K'
        expected_json_2 = hp.decode_base64(var_2)

        # when
        json_str_1 = target_1.to_json()
        json_str_2 = target_2.to_json()

        # then
        self.assertEqual(expected_json_1, json_str_1)
        self.assertEqual(expected_json_2, json_str_2)


if __name__ == '__main__':
    unittest.main()
