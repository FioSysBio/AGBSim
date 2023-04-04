import numpy as np
from cobra.io import load_model, load_json_model, read_sbml_model

def substrateFinder(reactions, directions, file_metabolic_model):

    print('direções: ' + str(directions))

    print('metabolitos: ' + str(reactions))

    metabolic_model = str(file_metabolic_model)
    print(metabolic_model)

    solver = 'glpk'

#     data_dir = './'
    data_dir = '/home/thiago/projetos/fiocruz/acbm-service/src/main/resources/static/'
    matfile_path = data_dir + metabolic_model

    print(matfile_path)

    model = load_json_model(matfile_path)
    print('--Sucesso ao carregar modelo do MATLAB--')

    v_out = np.zeros(len(reactions))

    for i in range(len(reactions)):
        if directions[i] == 0:
            v_out[i] = 0
        else:
            for j in range(len(reactions)):
                if directions[j] != 0:
                    reac = model.reactions.get_by_id(reactions[j])
                    if directions[j] == 1:
                        reac.lower_bound = 0
                    else:
                        reac.upper_bound = 0
            reac = model.reactions.get_by_id(reactions[i])
            if directions[i] == 1:
                reac.lower_bound = -20
            elif directions[i] == -1:
                reac.upper_bound = 20
            try:
                s = model.optimize()
                miu = s.objective_value
                v_out[i] = miu
            except:
                v_out[i] = 0
    return v_out

result = substrateFinder(reactions, directions, metabolic_model)