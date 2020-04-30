import sys
import io
import os
base_path = os.path.join(os.path.abspath(os.path.dirname(__name__)))
sys.path.append(os.path.join(base_path, 'menusys'))
from menusys import menu, menu_multi_select, make_menu_dict_from_dict, make_menu_dict_from_list, chunk_up_string


test_dict = {
    1: {'MENU': 'Item 1'},
    2: {'MENU': 'Item 2'},
    3: {'MENU': 'Item 3'},
    4: {'MENU': 'Item 4'},
    5: {'MENU': 'Item 5'},
    6: {'MENU': 'Item 6'},
}


def test_menu_response_good():
    f1 = sys.stdin
    f = io.StringIO('2')
    sys.stdin = f
    response = menu(test_dict, 'HEAD')
    sys.stdin = f1
    f.close()
    assert response == '2'


def test_menu_response_bad():
    f1 = sys.stdin
    f = io.StringIO('3')
    sys.stdin = f
    response = menu(test_dict, 'HEAD')
    sys.stdin = f1
    f.close()
    assert response != '2'


def test_menu_response_bad_no_quit():
    f1 = sys.stdin
    f = io.StringIO('3')
    sys.stdin = f
    response = menu(test_dict, 'HEAD', no_quit=True)
    sys.stdin = f1
    f.close()
    assert response != '2'


def test_multi_select_menu_response_good():
    f1 = sys.stdin
    f = io.StringIO('2\n3\nc\n')
    sys.stdin = f
    response = menu_multi_select(test_dict, 'HEAD')
    sys.stdin = f1
    f.close()
    assert response == ['2', '3']


def test_multi_select_menu_response_good_no_quit():
    f1 = sys.stdin
    f = io.StringIO('2\n3\nc\n')
    sys.stdin = f
    response = menu_multi_select(test_dict, 'HEAD', no_quit=True)
    sys.stdin = f1
    f.close()
    assert response == ['2', '3']


def test_make_menu_dict_from_dict():
    assert make_menu_dict_from_dict({1: {'a': 'some a data',
                                         'b': 'some b data',
                                         'c': 'some c data'}}, 'b') == {1: {'SUBDIC': {'c': 'some c data',
                                                                                       'b': 'some b data',
                                                                                       'a': 'some a data'},
                                                                            'MENU': 'some b data'}}


def test_make_menu_dict_from_list():
    assert make_menu_dict_from_list(['a', 'b', 'c']) == {1: {'MENU': 'a'}, 2: {'MENU': 'b'}, 3: {'MENU': 'c'}}


def test_chunk_up_string():
    assert chunk_up_string('this is a small sentence', size_of_chunk=3) == ['thi', 's i', 's a', ' sm', 'all',
                                                                            ' se', 'nte', 'nce']
