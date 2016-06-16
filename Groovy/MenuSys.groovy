package module
/*
##########################################################
# Script Name: MenuSys.groovy                            #
# Script Type: Groovy                                    #
# Updated By: Benjamin P. Trachtenberg                   #
# Date Written 6/16/2016                                 #
#                                                        #
# Description:                                           #
# Menu system tools                                      #
# This was created from my Python version of the same    #
# tools                                                  #
#                                                        #
##########################################################
Version: 1.1.0
Author: Benjamin P. Trachtenberg
Contact: e_ben_75-python@yahoo.com
*/

class MenuSys {

    def yes_no_dict = [
            1: ['MENU': "Yes"],
            2: ['MENU': "No"]
    ]

    def prim_sec_dict = [
            1: ['MENU': "Primary"],
            2: ['MENU': "Secondary"]
    ]

    public menu(menu_dictionary, menu_header, back_function=null) {
        def max_menu_entries = 0
        def options_list = menu_dictionary.keySet().asList()
        def good_selection = false

        println(menu_header)

        options_list.each { line ->
            def temp_line = line.toString()
            if (temp_line.length() == 1) {
                println("$line ) ${menu_dictionary[line]['MENU']}")
            } else if (temp_line.length() == 2) {
                println("$line) ${menu_dictionary[line]['MENU']}")
            }
            max_menu_entries += 1
        }

        if (back_function) {
            println("B ) Back")
        }

        println("Q ) Quit")

        def selected_option = get_cli_input("Please make a selection: ")
        selected_option = selected_option.toLowerCase()

        if (back_function) {
            if (selected_option == "b") {
                return back_function
            }
        }

        if (selected_option == "q") {
            return "q"
        }

        try {
            selected_option.toInteger()
        } catch (e) {
            selected_option = "0"
        }

        if (selected_option.toInteger() == 0) {
            good_selection = false
        } else if (selected_option.toInteger() > max_menu_entries) {
            good_selection = false
        } else {
            good_selection = true
        }

        while (!good_selection) {
            println("That selection is no good!!")
            selected_option = get_cli_input("Please make a selection: ")
            selected_option = selected_option.toLowerCase()
            if (back_function) {
                if (selected_option == "b") {
                    return back_function
                }
            }

            if (selected_option == "q") {
                return "q"
            }

            try {
                selected_option.toInteger()
            } catch (e) {
                selected_option = "0"
            }

            if (selected_option.toInteger() == 0) {
                good_selection = false
            } else if (selected_option.toInteger() > max_menu_entries) {
                good_selection = false
            } else {
                good_selection = true
            }
        }
        return selected_option
    }

    public menu_multi_select(menu_dictionary, menu_header, back_function=null) {
        def max_menu_entries = 0
        def selections_list = []
        def selected_option = null
        def options_list = menu_dictionary.keySet().asList()
        def good_selection = false
        def already_selected = false

        println(menu_header)

        options_list.each {
            max_menu_entries += 1
        }

        while (selected_option != 'c') {
            options_list.each { line ->
                def temp_line = line.toString()
                if (temp_line.length() == 1) {
                    try {
                        println("$line ) ${menu_dictionary[line]['MENU']}")
                    } catch (e) {
                        // do nothing
                    }
                } else if (temp_line.length() == 2) {
                    try {
                        println("$line) ${menu_dictionary[line]['MENU']}")
                    } catch (e) {
                        // do nothing
                    }
                }
            }


                if (back_function) {
                    println("B ) Back")
                }
                println("C ) Continue")
                println("Q ) Quit")

                selected_option = get_cli_input("Please make a selection: ")
                selected_option = selected_option.toLowerCase()

                if (back_function) {
                    if (selected_option == "b") {
                        return back_function
                    }
                }

                if (selected_option == "c") {
                    return selections_list
                }

                if (selected_option == "q") {
                    return "q"
                }

                try {
                    selected_option.toInteger()
                } catch (e) {
                    selected_option = "0"
                }

                if (selected_option.toInteger() == 0) {
                    good_selection = false
                } else if (selected_option.toInteger() > max_menu_entries) {
                    good_selection = false
                } else {
                    good_selection = true
                }

                while (!good_selection) {
                    println("That selection is no good!!")
                    selected_option = get_cli_input("Please make a selection: ")
                    selected_option = selected_option.toLowerCase()

                    if (back_function) {
                        if (selected_option == "b") {
                            return back_function
                        }
                    }

                    if (selected_option == "c") {
                        return selections_list
                    }

                    if (selected_option == "q") {
                        return "q"
                    }

                    try {
                        selected_option.toInteger()
                    } catch (e) {
                        selected_option = "0"
                    }

                    if (selected_option.toInteger() == 0) {
                        good_selection = false
                    } else if (selected_option.toInteger() > max_menu_entries) {
                        good_selection = false
                    } else {
                        good_selection = true
                    }

                }

                try {
                    menu_dictionary.remove(selected_option.toInteger())
                } catch (e) {
                    println("You have already made that selection!!  Please try again.")
                }

                already_selected = false
                selections_list.any { item ->
                    if (selected_option == item) {
                        already_selected = true
                    }

                }

                if (!already_selected) {
                    selections_list.add(selected_option)
                }

        }

    }

    public make_menu_dict_from_dict(orig_dict, dict_key_for_display) {
        def temp_dict = [:]
        def menu_new_key = 1

        orig_dict.each { key, value ->
            temp_dict[menu_new_key] = ['MENU': orig_dict[key][dict_key_for_display], 'SUBDIC': orig_dict[key]]
            menu_new_key += 1
        }
        return temp_dict
    }

    public make_menu_dict_from_list(orig_list) {
        def temp_dict = [:]
        def menu_new_key = 1

        orig_list.each { item ->
            temp_dict[menu_new_key] = ['MENU': item]
            menu_new_key += 1
        }
        return temp_dict
    }

    public static clear_screen() {
        System.out.flush()
    }

    private static get_cli_input(prompt) {
        print prompt
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        String input = br.readLine()
        return input
    }

}
