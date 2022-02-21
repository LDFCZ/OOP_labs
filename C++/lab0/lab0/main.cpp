#include <iostream>

#include "FileReader.h"
#include "StringParser.h"
#include "Context.h"
#include "CSVFileWriter.h"
#include "StatisticWriter.h"
#include "ConstSpace.h"

int main(int argc, char** argv)
{
	//checking the number of arguments
	if (argc != ConstSpace::MAX_ARG_COUNT)
	{
		std::cout << ConstSpace::ARG_ERR << std::endl;
		return ConstSpace::ERR_EXIT_CODE; // -1
	}

	Container::Context words_context;
	FileInterface::FileReader* ifile;

	//trying to open input file
	try
	{
		ifile = new FileInterface::FileReader(argv[ConstSpace::INPUT_FILE_ARG]);
	}
	catch (std::string err)
	{
		std::cout << err << std::endl;
		if (ifile != nullptr)
		{
			delete ifile;
		}
		return ConstSpace::ERR_EXIT_CODE; // -1
	}

	MainClasses::StringParser* str_parser = new MainClasses::StringParser(words_context, ifile);

	//trying to read input file
	try {
		str_parser->parse_strings();
	}
	catch (...) { //catch everything bcs i dont know the exact reason
		std::cout << ConstSpace::TXT_ERR << std::endl;
		delete str_parser;
		delete ifile;
		return ConstSpace::ERR_EXIT_CODE; // -1
	}

	delete str_parser;
	delete ifile;

	FileInterface::CSVFileWriter* ofile;

	//trying to open output file
	try
	{
		ofile = new FileInterface::CSVFileWriter(argv[ConstSpace::OUTPUT_FILE_ARG]);
	}
	catch (std::ios::failure)
	{
		std::cout << ConstSpace::CSV_ERR << std::endl;
		if (ofile != nullptr)
		{
			delete ofile;
		}
		return ConstSpace::ERR_EXIT_CODE; // -1
	}

	MainClasses::StatisticWriter* stat_writer = new MainClasses::StatisticWriter(words_context, ofile);

	//trying to write in output file
	try {
		stat_writer->write_statistic();
	}
	catch (...) { //same as input file
		std::cout << ConstSpace::CSV_ERR << std::endl;
		delete stat_writer;
		delete ofile;
		return ConstSpace::ERR_EXIT_CODE; // -1
	}
	delete stat_writer;
	delete ofile;

	return ConstSpace::SUC_EXIT_CODE; // 0
}


