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


if __name__ == '__main__':
    unittest.main()
