import unittest
import helpers as hp


class RemoveCharactersTest(unittest.TestCase):
    def test_remove_character_from_start(self):
        # given
        source_str = "baobab"
        chars_str = 'b'
        expected_result = "aoa"

        # when
        result = hp.remove_characters(source_str, chars_str)

        # then
        self.assertEqual(expected_result, result)

    def test_remove_character_from_end(self):
        # given
        source_str = "darkness"
        chars_str = 's'
        expected_result = "darkne"

        # when
        result = hp.remove_characters(source_str, chars_str)

        # then
        self.assertEqual(expected_result, result)

    def test_remove_character_in_the_middle(self):
        # given
        source_str = "debris"
        chars_str = 'b'
        expected_result = "deris"

        # when
        result = hp.remove_characters(source_str, chars_str)

        # then
        self.assertEqual(expected_result, result)

    def test_remove_character_from_begin_center_end(self):
        # given
        source_str = "amanda"
        chars_str = 'a'
        expected_result = "mnd"

        # when
        result = hp.remove_characters(source_str, chars_str)

        # then
        self.assertEqual(expected_result, result)

    def test_remove_two_characters(self):
        # given
        source_str = "Duis at tellus at urna condimentum mattis pellentesque id."
        expected_result = "Duis t tllus t urn condimntum mttis pllntsqu id."

        # when
        result = hp.remove_characters(source_str, 'a')
        result = hp.remove_characters(result, 'e')

        # then
        self.assertEqual(expected_result, result)


if __name__ == '__main__':
    unittest.main()
