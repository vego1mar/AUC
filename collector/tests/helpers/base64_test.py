import unittest
from collector.helpers import encode_base64
from collector.helpers import decode_base64


class Base64Test(unittest.TestCase):
    def test_encode_1(self):
        # given
        raw_text = r"Tadeusz Sznuk"
        expected_text = b"VGFkZXVzeiBTem51aw=="

        # when
        encoded_text = encode_base64(raw_text)

        # then
        self.assertEqual(expected_text, encoded_text)

    def test_decode_1(self):
        # given
        encoded_text = b"VGFkZXVzeiBTem51aw=="
        expected_text = r"Tadeusz Sznuk"

        # when
        decoded_text = decode_base64(encoded_text)

        # then
        self.assertEqual(expected_text, decoded_text)

    def test_encode_decode_2(self):
        # given
        raw_text = r"Mężny bądź, chroń pułk swój i sześć flag."
        expected_encoded_text = b"TcSZxbxueSBixIVkxbosIGNocm/FhCBwdcWCayBzd8OzaiBpIHN6ZcWbxIcgZmxhZy4="

        # when
        encoded_text = encode_base64(raw_text)
        decoded_text = decode_base64(encoded_text)

        # then
        self.assertEqual(expected_encoded_text, encoded_text)
        self.assertEqual(raw_text, decoded_text)


if __name__ == '__main__':
    unittest.main()
