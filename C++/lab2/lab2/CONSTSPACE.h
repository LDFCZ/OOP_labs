#pragma once

namespace CONSTSPACE {
	static const std::string INPUT_FILE_WORKFLOW_ERROR = "Workflow bad input file name or path : ";
	static const std::string BAD_SYNTAX_WORKFLOW_ERROR = "Workflow bad syntax : ";
	static const std::string NO_END_WORKFLOW_ERROR = "Workflow bad syntax : no end of program! ";
	static const std::string BAD_ID_WORKFLOW_ERROR = "Workflow bad syntax :\n bad id! : ";
	static const std::string DUPLICATE_ID_WORKFLOW_ERROR = "Workflow bad syntax :\n bad id! duplicate id! :";
	static const std::string INCOMPSTIBLE_WORKERS_WORKFLOW_ERROR = "IncompatibleWorkersTypes ";

	static const std::string OUTPUT_FILE_DUMPWORKER_ERROR = "Dump error bad input file name or path : ";
	static const std::string INPUT_FILE_WRITERWORKER_ERROR = "Writer error bad input file name or path : ";
	static const std::string OUTPUT_FILE_RIDERWORKER_ERROR = "Reader error bad input file name or path : ";

	static const std::string SPACE = " ";
	static const std::wstring WEQUAL = L"=";
	static const std::wstring WARROW = L"->";
	static const std::wstring WSPACE = L" ";
	static const wchar_t WCHAR_SPACE = L' ';

	static const wchar_t ENDL = L'\n';

	static const std::wstring EMPTY_STR = L"";
	static const std::wstring CODE_START = L"desc";
	static const std::wstring CODE_END = L"csed";

	static const char MAIN_FILE_NAME_ARG = 1;

	static const char FILE_NAME_ARG = 3;
	static const char GREP_WORD_ARG = 3;
	static const char REPLACEABLE_WORD_ARG = 3;
	static const char SUBSTITUTE_WORD_ARG = 4;
	static const char COMMAND_ARG = 2;
	static const char NUMBER_ARG = 0;
	static const char EQUAL_ARG = 1;

	static const char DUMB_ARG_COUNT = 4;
	static const char GREP_ARG_COUNT = 4;
	static const char READ_ARG_COUNT = 4;
	static const char REPLACE_ARG_COUNT = 5;
	static const char SORT_ARG_COUNT = 3;
	static const char WRITE_ARG_COUNT = 4;

	static const std::string DUMPWORKER_ARG_ERROR = "Dumper error bad arguments or count";
	static const std::string GREPWORKER_ARG_ERROR = "Greper error bad arguments or count";
	static const std::string READWORKER_ARG_ERROR = "Reader error bad arguments or count";
	static const std::string REPLACEWORKER_ARG_ERROR = "Replacer error bad arguments or count";
	static const std::string SORTWORKER_ARG_ERROR = "Sorter error bad arguments or count";
	static const std::string WRITEWORKER_ARG_ERROR = "Writer error bad arguments or count";

	static const std::wstring READ_COMMAND = L"readfile";
	static const std::wstring WRITE_COMMAND = L"writefile";
	static const std::wstring GREP_COMMAND = L"grep";
	static const std::wstring SORT_COMMAND = L"sort";
	static const std::wstring REPLACE_COMMAND = L"replace";
	static const std::wstring DUMP_COMMAND = L"dump";
}