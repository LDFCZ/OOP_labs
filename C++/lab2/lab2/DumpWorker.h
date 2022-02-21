#pragma once

#include "Worker.h"
#include "Context.h"

namespace MainWorkers {

    class DumpWorker : public Interfaces::Worker {
    private:
        std::wstring _file_name;
        std::wofstream _file;

        MainContext::Context& _context;

        const Interfaces::Types _in_type = Interfaces::Types::TEXT;
        const Interfaces::Types _out_type = Interfaces::Types::TEXT;
    public:
        DumpWorker(std::vector<std::wstring>&, MainContext::Context&);

        ~DumpWorker();

        void work() override;

        Interfaces::Types get_input_type() const override;

        Interfaces::Types get_output_type() const override;
    };
}

