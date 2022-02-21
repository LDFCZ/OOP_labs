#pragma once

#include "Worker.h"
#include "Context.h"

namespace MainWorkers {
	class WriterWorker : public Interfaces::Worker {
    private:
        std::wstring _file_name;
        std::wofstream _file;

        MainContext::Context& _context;

        const Interfaces::Types _in_type = Interfaces::Types::TEXT;
        const Interfaces::Types _out_type = Interfaces::Types::TEXT;
    public:
        WriterWorker(std::vector<std::wstring>&, MainContext::Context&);

        ~WriterWorker();

        void work() override;

        Interfaces::Types get_input_type() const override;

        Interfaces::Types get_output_type() const override;
	};
}
