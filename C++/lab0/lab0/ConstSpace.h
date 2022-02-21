#pragma once

#include <string>

namespace ConstSpace {
	static const std::wstring FIRST_COLUMN_NAME = L"words";
	static const std::wstring SECOND_COLUMN_NAME = L"count";
	static const std::wstring THIRD_COLUMN_NAME = L"frequency";

	static const wchar_t ENG_ALPHABET_START_LOW = L'a';
	static const wchar_t ENG_ALPHABET_END_LOW = L'z';
	static const wchar_t ENG_ALPHABET_START_HIGH = L'A';
	static const wchar_t ENG_ALPHABET_END_HIGH = L'Z';
	static const wchar_t RUS_ALPHABET_START_LOW = L'а';
	static const wchar_t RUS_ALPHABET_END_LOW = L'я';
	static const wchar_t RUS_ALPHABET_START_HIGH = L'А';
	static const wchar_t RUS_ALPHABET_END_HIGH = L'Я';
	static const wchar_t NUM_ALPHABET_START = L'0';
	static const wchar_t NUM_ALPHABET_END = L'9';
	static const wchar_t ENDL = L'\n';

	static const wchar_t SEPARATOR = L',';

	static const std::string ERR_STR = "bad input file: ";
	static const std::string CSV_ERR = "some troubles with output csv file!";
	static const std::string ARG_ERR = "bad number of args";
	static const std::string TXT_ERR = "some troubles with input txt file!";

	static const int PROPORTION = 100;

	static const int ERR_EXIT_CODE = -1;
	static const int SUC_EXIT_CODE = 0;

	static const int INPUT_FILE_ARG = 1;
	static const int OUTPUT_FILE_ARG = 2;

	static const int MAX_ARG_COUNT = 3;

	static const std::wstring EMPTY_WSTR = L"";
}