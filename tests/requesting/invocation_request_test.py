import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class InvocationRequestTestCase(unittest.TestCase):
    def test_to_json_method(self):
        # given
        request = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.FetchAttribute("web"))
        exp = b'ewoJInRhcmdldCI6IHsKCQkic2V0X25hbWUiOiAiV0VCIiwKCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZSwKCQkiY29s' \
              b'bGVjdGlibGVfbmFtZSI6ICJnZW5lcmFsIgoJfSwKCSJmZXRjaF9hdHRyaWJ1dGVfdHJpZ2dlciI6IHsKCQkibmFtZSI6ICJ3ZWIi' \
              b'Cgl9Cn0K'
        expected = hp.decode_base64(exp)

        # when
        result = request.to_json()

        # then
        self.assertEqual(expected, result)


if __name__ == '__main__':
    unittest.main()
