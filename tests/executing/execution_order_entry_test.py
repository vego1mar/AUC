import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class ExecutionOrderEntryTestCase(unittest.TestCase):
    def test_to_json(self):
        # given
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.FetchAttribute('gen2'))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, 'gen1'), tr.Find('gen3'))
        chain_request = (req_1, req_2)
        entry = ex.ExecutionOrderEntry(chain_request, 'http://www.something.com')
        jstr = b'ewoJCSJjaGFpbl9yZXF1ZXN0IjogWwoJCQkJewoJCQkJCQkidGFyZ2V0IjogewoJCQkJCQkJCSJzZXRfbmFtZSI6ICJXRUIiL' \
               b'AoJCQkJCQkJCSJpc19nYXRoZXJpbmdfcmVxdWVzdCI6IGZhbHNlLAoJCQkJCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbm' \
               b'VyYWwiCgkJCQkJCX0sCgkJCQkJCSJ0cmlnZ2VyIjogewoJCQkJCQkJCSJ0cmlnZ2VyX3R5cGUiOiAiZmV0Y2hfYXR0cmlidXR' \
               b'lX3RyaWdnZXIiLAoJCQkJCQkJCSJhdHRyX25hbWUiOiAiZ2VuMiIKCQkJCQkJfQoJCQkJfSwKCQkJCXsKCQkJCQkJInRhcmdl' \
               b'dCI6IHsKCQkJCQkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkJCQkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogdHJ1ZSwKC' \
               b'QkJCQkJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW4xIgoJCQkJCQl9LAoJCQkJCQkidHJpZ2dlciI6IHsKCQkJCQkJCQkidH' \
               b'JpZ2dlcl90eXBlIjogImZpbmRfdHJpZ2dlciIsCgkJCQkJCQkJInRleHQiOiAiZ2VuMyIKCQkJCQkJfQoJCQkJfQoJCV0sCgk' \
               b'JImh0bWxfZGF0YSI6ICJodHRwOi8vd3d3LnNvbWV0aGluZy5jb20iCn0='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = entry.to_json()
        obj = ex.ExecutionOrderEntry.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(obj.compare(entry))


if __name__ == '__main__':
    unittest.main()
