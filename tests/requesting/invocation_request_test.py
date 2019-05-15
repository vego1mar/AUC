import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class InvocationRequestTestCase(unittest.TestCase):
    def test_json_1_find_trigger(self):
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

    def test_json_2_find_next_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.FindNext(hp.decode_base64(b'c29tZXRoaW5n'))
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZ' \
               b'SwKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cG' \
               b'UiOiAiZmluZF9uZXh0X3RyaWdnZXIiLAoJCQkJInRleHQiOiAic29tZXRoaW5nIgoJCX0KfQ=='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_3_retrieve_tags_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.RetrieveTags('code', tr.TagType.META, 1001)
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxz' \
               b'ZSwKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5' \
               b'cGUiOiAicmV0cmlldmVfdGFnc190cmlnZ2VyIiwKCQkJCSJ0YWdfbmFtZSI6ICJjb2RlIiwKCQkJCSJ0YWdfdHlwZSI6ICJN' \
               b'RVRBIiwKCQkJCSJhbW91bnQiOiAxMDAxCgkJfQp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_4_select_element_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.SelectElement(19845)
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZS' \
               b'wKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cGUi' \
               b'OiAic2VsZWN0X2VsZW1lbnRfdHJpZ2dlciIsCgkJCQkicG9zaXRpb24iOiAxOTg0NQoJCX0KfQ=='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_5_fetch_attribute_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.LIST)
        trigger = tr.FetchAttribute(hp.decode_base64(b'YWx0LWRvd25sb2Fk'))
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiTElTVCIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZS' \
               b'wKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cGUi' \
               b'OiAiZmV0Y2hfYXR0cmlidXRlX3RyaWdnZXIiLAoJCQkJImF0dHJfbmFtZSI6ICJhbHQtZG93bmxvYWQiCgkJfQp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_6_get_regex_match_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.GetRegexMatch(hp.decode_base64(b'Xltcd10rID8gW0EtRmctel0rJA=='))
        request = rq.InvocationRequest(target, trigger)
        string = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxz' \
                 b'ZSwKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5' \
                 b'cGUiOiAiZ2V0X3JlZ2V4X21hdGNoX3RyaWdnZXIiLAoJCQkJInBhdHRlcm4iOiAiXltcXHddKyA/IFtBLUZnLXpdKyQiCgkJ' \
                 b'fQp9'
        expected_json_str = hp.decode_base64(string)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_7_cut_aside_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.CutAside(10100110, 10110011)
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZS' \
               b'wKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cGUi' \
               b'OiAiY3V0X2FzaWRlX3RyaWdnZXIiLAoJCQkJImxlZnQiOiAxMDEwMDExMCwKCQkJCSJyaWdodCI6IDEwMTEwMDExCgkJfQp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_8_set_workspace_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.SetWorkspace(hp.decode_base64(b'Q3VyIGZ1Z2lzPw=='))
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZS' \
               b'wKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cGUi' \
               b'OiAic2V0X3dvcmtzcGFjZV90cmlnZ2VyIiwKCQkJCSJ0ZXh0IjogIkN1ciBmdWdpcz8iCgkJfQp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_9_get_subset_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.GetSubset('0', 0)
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZS' \
               b'wKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cGUi' \
               b'OiAiZ2V0X3N1YnNldF90cmlnZ2VyIiwKCQkJCSJiZWdpbiI6ICIwIiwKCQkJCSJlbmQiOiAwCgkJfQp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_10_add_text_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.AddText(hp.decode_base64(b'MC1w'), hp.decode_base64(b'MS1h'))
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZ' \
               b'SwKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cG' \
               b'UiOiAiYWRkX3RleHRfdHJpZ2dlciIsCgkJCQkicHJlcGVuZCI6ICIwLXAiLAoJCQkJImFwcGVuZCI6ICIxLWEiCgkJfQp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))

    def test_json_11_delete_trigger(self):
        # given
        target = rq.Target(rq.SpaceName.WORK)
        trigger = tr.Delete(hp.decode_base64(b'SHVtYmxl'), hp.decode_base64(b'IEJ1bmRsZQ=='))
        request = rq.InvocationRequest(target, trigger)
        jstr = b'ewoJCSJ0YXJnZXQiOiB7CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZ' \
               b'SwKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkidHJpZ2dlciI6IHsKCQkJCSJ0cmlnZ2VyX3R5cG' \
               b'UiOiAiZGVsZXRlX3RyaWdnZXIiLAoJCQkJInN0cmluZyI6ICJIdW1ibGUiLAoJCQkJImNoYXJhY3RlcnMiOiAiIEJ1bmRsZSI' \
               b'KCQl9Cn0='
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = request.to_json()
        obj = rq.InvocationRequest.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(request.compare(obj))


if __name__ == '__main__':
    unittest.main()
