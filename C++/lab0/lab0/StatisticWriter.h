#pragma once

#include "Context.h"
#include"CSVFileWriter.h"

namespace MainClasses {

	class StatisticWriter
	{
	private:
		Container::Context& word_context;
		FileInterface::CSVFileWriter* ofile;

	public:
		StatisticWriter(Container::Context& word_context, FileInterface::CSVFileWriter* ofile);

		void write_statistic();
	};
}
