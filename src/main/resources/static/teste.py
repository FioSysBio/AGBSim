import cobra
import numpy as np
import scipy.io
from cobra.io import load_json_model, save_json_model, load_matlab_model, save_matlab_model, read_sbml_model, write_sbml_model

def substrateFinder(metabolites, directions, metabolic_model):
    metabolites = ["GLCNtex"]
    directions = np.array([-1])
    metabolic_model = str('iJO1366TRFBA')



    solver = 'glpk'

#     model = scipy.io.loadmat('iJO1366TRFBA.mat')
    matfile_path = 'iJO1366TRFBA.mat'
#     model = load_matlab_model(matfile_path)
    model = load_model("iJO1366")
    print('--Sucesso ao carregar modelo do MATLAB--')


    print(len(metabolites))
    # cobra.test.create_test_model(metabolic_model)
    # model = cobra.test.create_test_model(metabolic_model)
    v_out = np.zeros(len(metabolites))

    print(v_out)
    #
    for i in range(len(metabolites)):
        if directions[i] == 0:
            v_out[i] = 0
        else:
            for j in range(len(metabolites)):
                if directions[j] != 0:
                    if directions[j] == 1:
                        print(model.reactions)
#                         model.reactions[metabolites[j]].lower_bound = 0
                    else:
                        print(model.reactions[0])
                        model.reactions[metabolites[j]].upper_bound = 0

            if directions[i] == 1:
                model.reactions[metabolites[i]].lower_bound = -20
            elif directions[i] == -1:
                model.reactions[metabolites[i]].upper_bound = 20

            try:
                s = model.optimize()
                miu = s.objective_value
                v_out[i] = miu
            except:
                v_out[i] = 0
    print(v_out)
    return v_out

result = substrateFinder('metabolites', 'directions', 'metabolic_model')