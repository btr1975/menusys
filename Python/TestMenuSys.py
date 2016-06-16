import unittest
import sys
import io
from modMenuSys import menu, menu_multi_select

test_dict = {
    1: {'MENU': 'Item 1'},
    2: {'MENU': 'Item 2'},
    3: {'MENU': 'Item 3'},
    4: {'MENU': 'Item 4'},
    5: {'MENU': 'Item 5'},
    6: {'MENU': 'Item 6'},
}


class TestMenu(unittest.TestCase):

    def test_menu_response(self):
        """Test if menu responds correctly"""
        f1 = sys.stdin
        f = io.StringIO('2')
        sys.stdin = f
        response = menu(test_dict, 'HEAD')
        sys.stdin = f1
        f.close()
        self.assertEqual('2', response)

    def test_menu_response_2(self):
        """Test if menu responds correctly"""
        f1 = sys.stdin
        f = io.StringIO('3')
        sys.stdin = f
        response = menu(test_dict, 'HEAD')
        sys.stdin = f1
        f.close()
        self.assertNotEqual('2', response)


class TestMultiMenu(unittest.TestCase):

    def test_menu_response(self):
        """Test if multi menu responds correctly"""
        f1 = sys.stdin
        f = io.StringIO('2\n3\nc\n')
        sys.stdin = f
        response = menu_multi_select(test_dict, 'HEAD')
        sys.stdin = f1
        f.close()
        self.assertListEqual(['2', '3'], response)


if __name__ == '__main__':
    unittest.main()
