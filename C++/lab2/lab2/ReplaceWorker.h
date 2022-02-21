#pragma once

#include "Worker.h"
#include "Context.h"

namespace MainWorkers {

    class ReplaceWorker : public Interfaces::Worker {
    private:
        MainContext::Context& _context;
        
        std::wstring _replaceable;
        std::wstring _substitute;

        const Interfaces::Types _in_type = Interfaces::Types::TEXT;
        const Interfaces::Types _out_type = Interfaces::Types::TEXT;
    public:
        ReplaceWorker(std::vector<std::wstring>&, MainContext::Context&);

        void work() override;

        Interfaces::Types get_input_type() const override;

        Interfaces::Types get_output_type() const override;
    };
}

