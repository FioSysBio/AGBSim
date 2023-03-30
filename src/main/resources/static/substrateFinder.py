import numpy as np
# import cobra as cb
# import scipy.io
from cobra.io import load_model, load_json_model, save_json_model, load_matlab_model, save_matlab_model, read_sbml_model, write_sbml_model
# from pathlib import Path
# from cobra import Model, Reaction, Metabolite

def substrateFinder(metabolites, directions, file_metabolic_model):
#     metabolites = ["glyc_p", "ump_p"]
    # metabolites = ["GLCNtex"]
#     directions = np.array([-1, 1])
    print('direções: ' + str(directions))

    # print('qtd de metabolitos: ' + str(len(metabolites)))
    print('metabolitos: ' + str(metabolites))

    metabolic_model = str(file_metabolic_model)
    print(metabolic_model)

    solver = 'glpk'

#     data_dir = './'
    data_dir = '/home/thiago/projetos/fiocruz/acbm-service/src/main/resources/static/'
    matfile_path = data_dir + metabolic_model
    # model = scipy.io.loadmat('iJO1366TRFBA.mat')
    # model = load_matlab_model(str(matfile_path.resolve()))
#     model = load_model(matfile_path)
    model = load_json_model(matfile_path)
    print('--Sucesso ao carregar modelo do MATLAB--')

    v_out = np.zeros(len(metabolites))
    # print('npzeros: ' + str(np.zeros((2,2))))

    # print('inicialização do v_out:' + str(v_out))
    #
    # print(range(len(metabolites)))
    for i in range(len(metabolites)):
        if directions[i] == 0:
            v_out[i] = 0
        else:
            for j in range(len(metabolites)):
                if directions[j] != 0:
                    if directions[j] == 1:
                        met = model.metabolites.get_by_id(metabolites[j])
                        # print(met.reactions)
                        # print('metabolito: ' + str(metabolites[j]))
                        for r in met.reactions:
                            # print('antes---------------------')
                            # print(r.name)
                            # print('lower_bound da reação: ' + str(r.lower_bound))
                            r.lower_bound = 0
                            # print('depois---------------------')
                            # print(r.name)
                            # print('lower_bound da reação: ' + str(r.lower_bound))
                    else:
                        met = model.metabolites.get_by_id(metabolites[j])
                        # print(met.reactions)
                        # print('metabolito: ' + str(metabolites[j]))
                        for r in met.reactions:
                            # print('antes---------------------')
                            # print(r.name)
                            # print('upper_bound da reação: ' + str(r.upper_bound))
                            r.upper_bound = 0
                            # print('depois---------------------')
                            # print(r.name)
                            # print('upper_bound da reação: ' + str(r.upper_bound))
            if directions[i] == 1:
                met = model.metabolites.get_by_id(metabolites[i])
                # print(met.reactions)
                # print('metabolito: ' + str(metabolites[j]))
                for r in met.reactions:
                    # print('antes---------------------')
                    # print(r.name)
                    # print('lower_bound da reação: ' + str(r.lower_bound))
                    r.lower_bound = -20
                    # print('depois---------------------')
                    # print(r.name)
                    # print('lower_bound da reação: ' + str(r.lower_bound))
            elif directions[i] == -1:
                # model.reactions[metabolites[i]].upper_bound = 20
                met = model.metabolites.get_by_id(metabolites[i])
                # print(met.reactions)
                # print('metabolito: ' + str(metabolites[j]))
                for r in met.reactions:
                    # print('antes---------------------')
                    # print(r.name)
                    # print('lower_bound da reação: ' + str(r.upper_bound))
                    r.upper_bound = 20
                    # print('depois---------------------')
                    # print(r.name)
                    # print('lower_bound da reação: ' + str(r.upper_bound))
            try:
                s = model.optimize()
                miu = s.objective_value
                v_out[i] = miu
            except:
                v_out[i] = 0
    # print('saída: ' + str(v_out))
    # print(model.metabolites)
    return v_out

result = substrateFinder(metabolites, directions, metabolic_model)
# print('saída: ' + str(result))