import numpy as np
from cobra.io import load_model, load_json_model, read_sbml_model

def substrateFinder(reactions, file_metabolic_model):

    metabolic_model = str(file_metabolic_model)
    print(metabolic_model)

    data_dir = '/home/thiago/projetos/fiocruz/acbm-service/src/main/resources/static/'
    json_path = data_dir + metabolic_model

    print(json_path)

    model = load_json_model(json_path)

    v_out = True
    for i in range(len(reactions)):
        reac = model.reactions.get_by_id(reactions[i])
        if reac is None:
            v_out = False

    return v_out

result = substrateFinder(reactions, metabolic_model)