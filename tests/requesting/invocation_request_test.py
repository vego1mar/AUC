import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class InvocationRequestTestCase(unittest.TestCase):
    def test_json(self):
        # given
        target = rq.Target(rq.SpaceName.WEB)
        trigger = tr.Find(hp.decode_base64(b'ZmluZC10ZXh0'))
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV0VCIiwKCQkJCSJpc19nYXRoZXJpbmdfcmVxdWVzdCI6IGZhbHNlLA' \
               b'oJCQkJImNvbGxlY3RpYmxlX25hbWUiOiAiZ2VuZXJhbCIKCQl9LAoJCSJ0cmlnZ2VyIjogewoJCQkJInRyaWdnZXJfdHlwZSI6' \
               b'ICJmaW5kX3RyaWdnZXIiLAoJCQkJInRleHQiOiAiZmluZC10ZXh0IgoJCX0KfQ=='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))


if __name__ == '__main__':
    unittest.main()
